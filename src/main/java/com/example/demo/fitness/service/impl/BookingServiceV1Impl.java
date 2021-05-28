package com.example.demo.fitness.service.impl;

import com.example.demo.fitness.datastore.BookingDetailsRepo;
import com.example.demo.fitness.datastore.FitnessClassRepo;
import com.example.demo.fitness.entities.BookingDetailsDTO;
import com.example.demo.fitness.entities.CancelStatus;
import com.example.demo.fitness.entities.FitnessClass;
import com.example.demo.fitness.request.BookingRequestV1;
import com.example.demo.fitness.request.CancelRequestV1;
import com.example.demo.fitness.response.BookingResponseV1;
import com.example.demo.fitness.response.BookingStatus;
import com.example.demo.fitness.response.CancelResponseV1;
import com.example.demo.fitness.service.BookingServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author akshay on 28/05/21
 */
@Service
public class BookingServiceV1Impl implements BookingServiceV1 {

    @Autowired
    private FitnessClassRepo fitnessClassRepo;

    @Autowired
    private BookingDetailsRepo bookingDetailsRepo;

    @Override
    public BookingResponseV1 bookClass(BookingRequestV1 requestV1) {
        BookingResponseV1 bookingResponseV1 = new BookingResponseV1(requestV1.getBookingId());
        if(!fitnessClassRepo.getClassIdClassDetailsMap().containsKey(requestV1.getClassId())){
            bookingResponseV1.setStatus(BookingStatus.ERROR);
            bookingResponseV1.setMessage("Invalid classID");
            return bookingResponseV1;
        }
        FitnessClass fitnessClass = fitnessClassRepo.getClassIdClassDetailsMap().get(requestV1.getClassId());
        if(fitnessClass.getCurrentBookingCount()>=fitnessClass.getFitnessClassMetadata().getCapacity()
                && fitnessClass.getBookingQueue().size() >= fitnessClass.getFitnessClassMetadata().getQueueCapacity()){
            bookingResponseV1.setStatus(BookingStatus.PROCESSING_ERROR);
            bookingResponseV1.setMessage("Lot of queued requests. Not accepting more bookings");
        }
        else if(fitnessClass.getCurrentBookingCount()>=fitnessClass.getFitnessClassMetadata().getCapacity()){
            fitnessClass.getBookingQueue().add(requestV1);
            bookingResponseV1.setStatus(BookingStatus.QUEUED);
            bookingResponseV1.setMessage("Class booking request added to queue");
        }else{
            bookingResponseV1.setStatus(BookingStatus.SUCCESS);
            bookingResponseV1.setMessage("Class booked");
            fitnessClass.incrBookingCount();
        }
        BookingDetailsDTO bookingDetailsDTO = new BookingDetailsDTO(requestV1.getBookingId(), requestV1.getUserId(), requestV1.getClassId(), bookingResponseV1.getStatus());
        bookingDetailsRepo.getBookingDetailsDTOMap().put(requestV1.getBookingId(), bookingDetailsDTO);

        bookingResponseV1.setFitnessClass(fitnessClass);
        return bookingResponseV1;
    }

    @Override
    public CancelResponseV1 cancelBooking(CancelRequestV1 cancelRequest) {
        if(!bookingDetailsRepo.getBookingDetailsDTOMap().containsKey(cancelRequest.getBookingId())){
            return new CancelResponseV1("Invalid booking Id", cancelRequest.getBookingId(), null, CancelStatus.ERROR);
        }
        BookingDetailsDTO detailsDTO=bookingDetailsRepo.getBookingDetailsDTOMap().get(cancelRequest.getBookingId());
        FitnessClass fitnessClass = fitnessClassRepo.getClassIdClassDetailsMap().get(detailsDTO.getClassId());
        long currentTime = System.currentTimeMillis();
        long classStartTime = fitnessClass.getStartTime();

        long mins = (currentTime-classStartTime)/(1000*60);
        if(mins<=30){
            return new CancelResponseV1("Cancelling not allowed as Class is about to start", cancelRequest.getBookingId(), null, CancelStatus.ERROR);
        }
        detailsDTO.setStatus(BookingStatus.CANCELLED);
        fitnessClass.decrBookingCount();
        if(!fitnessClass.getBookingQueue().isEmpty()){
            BookingResponseV1 responseV1 = bookClass(fitnessClass.getBookingQueue().peek());
            if(responseV1.getStatus()==BookingStatus.SUCCESS){
                fitnessClass.getBookingQueue().poll();
            }
        }
        return new CancelResponseV1("Successfully canceled", cancelRequest.getBookingId(), fitnessClass, CancelStatus.SUCCESS);
    }
}

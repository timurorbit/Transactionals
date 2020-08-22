package com.timplant.controllers;

import com.timplant.controllers.requestmodels.TransactionRequest;
import com.timplant.models.Transaction;
import com.timplant.services.transaction.TransactionNotFoundException;
import com.timplant.services.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component
@Path("/transactionservice")
public class TransactionsController {

    @Autowired
    protected TransactionService transactionService;

    @Context
    UriInfo uri;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("transactions/{id}")
    public Response getTransactionById(@PathParam("id") Long id) throws Exception {
        try {
            return Response.status(200).entity(
                    transactionService.getTransactionById(id)
            ).build();
        } catch (TransactionNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("transactions/{id}/sum")
    public Response getSumOfTransaction(@PathParam("id") long id) throws Exception {
        try {
            return Response.status(200).entity(
                    transactionService.calculateSum(transactionService.getTransactionById(id))
            ).build();
        } catch (TransactionNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @POST
    @Path("transactions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewTransaction(TransactionRequest transactionRequest) {

        Transaction transaction;
        if (null != transactionRequest.parentId) {
            try {
                transaction = transactionService.createNewTransaction(transactionRequest.amount, transactionRequest.type, transactionRequest.parentId);
            } catch (TransactionNotFoundException e) {
                throw new NotAcceptableException(e.getMessage());
            }

        } else
            transaction = transactionService.createNewTransaction(transactionRequest.amount, transactionRequest.type);

        return Response.status(201).
                header("Location", uri.getBaseUri() + "transactionservice/transactions/" + transaction.getId())
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("types/{type}")
    public Response getTransactionById(@PathParam("type") String type) throws Exception {
        try {
            return Response.status(200).entity(
                    transactionService.getTransactionsByType(type)
            ).build();
        } catch (TransactionNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

}

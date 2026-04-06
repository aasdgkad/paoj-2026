package com.pao.laboratory07.exercise1;

import com.pao.laboratory07.exercise1.exceptions.*;

public class Order {
    private OrderState currentState;

    public Order(OrderState initialState) {
        this.currentState = initialState;
    }

    public void nextState() throws OrderIsAlreadyFinalException {
        switch (currentState) {
            case PLACED:
                currentState = OrderState.PROCESSED;
                System.out.println("Order state updated to: PROCESSED");
                break;
            case PROCESSED:
                currentState = OrderState.SHIPPED;
                System.out.println("Order state updated to: SHIPPED");
                break;
            case SHIPPED:
                currentState = OrderState.DELIVERED;
                System.out.println("Order state updated to: DELIVERED");
                break;
            case DELIVERED:
            case CANCELED:
                throw new OrderIsAlreadyFinalException("Cannot transition from " + currentState + " state.");
        }
    }

    public void cancel() throws CannotCancelFinalOrderException {
        switch (currentState) {
            case PLACED:
            case PROCESSED:
            case SHIPPED:
                currentState = OrderState.CANCELED;
                break;
            case DELIVERED:
            case CANCELED:
                throw new CannotCancelFinalOrderException();
        }
    }

    public void undoState() throws CannotRevertInitialOrderStateException {
        switch (currentState) {
            case PROCESSED:
                currentState = OrderState.PLACED;
                break;
            case SHIPPED:
                currentState = OrderState.PROCESSED;
                break;
            case DELIVERED:
                currentState = OrderState.SHIPPED;
                break;
            case PLACED:
            case CANCELED:
                throw new CannotRevertInitialOrderStateException();
        }
    }
}
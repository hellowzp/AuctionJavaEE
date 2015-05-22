package exception;

import javax.ejb.*;

@ApplicationException(rollback = true)
public class AuctionException extends RuntimeException {

    public AuctionException() {
    }

    public AuctionException(String message) {
        super(message);
    }
}

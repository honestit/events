package honestit.projects.eventuator.accounts.activation;

public interface ActivationResponse {

    boolean isSuccess();

    FailReason getFailReason();

    enum FailReason {
        INVALID_TOKEN,
        EXPIRED_TOKEN,
        ALREADY_ACTIVE,
    }
}

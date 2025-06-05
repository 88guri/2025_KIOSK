package domain;

public class AuthorizationResult {
    private final boolean approved;
    private final String reason;

    public AuthorizationResult(boolean approved, String reason) {
        this.approved = approved;
        this.reason = reason;
    }

    public boolean isApproved() { return approved; }
    public String getReason() { return reason; }
}

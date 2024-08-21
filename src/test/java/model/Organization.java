package model;

public class Organization {
    private String organizationId;
    private String status;
    private long timestamp;
    private boolean contractSigned;
    private Branch[] branchOffice;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isContractSigned() {
        return this.contractSigned;
    }

    public void setContractSigned(boolean contractSigned) {
        this.contractSigned = contractSigned;
    }

    public Branch[] getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(Branch[] branchOffice) {
        this.branchOffice = branchOffice;
    }
}

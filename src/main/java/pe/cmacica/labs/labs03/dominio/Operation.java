package pe.cmacica.labs.labs03.dominio;

import java.io.Serializable;

public class Operation implements Serializable {

    private int status;
    private String txId;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }
}

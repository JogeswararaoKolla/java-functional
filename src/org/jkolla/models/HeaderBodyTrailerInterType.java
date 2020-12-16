package org.jkolla.models;

public class HeaderBodyTrailerInterType {
   private Integer ordering;
   private Integer transactionId;
   private  String kind;
   private HeaderType header;
   private BodyType body;
   private TrailerType trailer;

    public HeaderBodyTrailerInterType(Integer ordering, Integer transactionId, String kind, HeaderType header, BodyType body, TrailerType trailer) {
        this.ordering = ordering;
        this.transactionId = transactionId;
        this.kind = kind;
        this.header = header;
        this.body = body;
        this.trailer = trailer;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public String getKind() {
        return kind;
    }

    public HeaderType getHeader() {
        return header;
    }

    public BodyType getBody() {
        return body;
    }

    public TrailerType getTrailer() {
        return trailer;
    }

    @Override
    public String toString() {
        return "HeaderBodyTrailerInterType{" +
                "ordering=" + ordering +
                ", transactionId=" + transactionId +
                ", kind='" + kind + '\'' +
                ", header=" + header +
                ", body=" + body +
                ", trailer=" + trailer +
                '}';
    }
}

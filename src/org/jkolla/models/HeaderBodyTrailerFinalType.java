package org.jkolla.models;

public class HeaderBodyTrailerFinalType {
    private String kind;
    private HeaderType header;
    private BodyType body;
    private TrailerType trailer;

    public HeaderBodyTrailerFinalType(String kind, HeaderType header, BodyType body, TrailerType trailer) {
        this.kind = kind;
        this.header = header;
        this.body = body;
        this.trailer = trailer;
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

        String string = new String() ;
        if ( kind == "H" )
            string = header.getTransactionId() + "," + header.getCustomerName() + "," + header.getNumItems() ;
        else if( kind == "B" )
            string = body.getItem() + ","  + body.getQuantity() + "," + body.getCost();
        else if( kind == "T")
            string =  trailer.getTotalQuantity() + "," + trailer.getTotalCost();

        return string;
    }

//    @Override
//    public String toString() {
//        return "HeaderBodyTrailerFinalType{" +
//                "kind='" + kind + '\'' +
//                ", header=" + header +
//                ", body=" + body +
//                ", trailer=" + trailer +
//                '}';
//    }

}

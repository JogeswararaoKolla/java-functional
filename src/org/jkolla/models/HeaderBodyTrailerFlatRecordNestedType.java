package org.jkolla.models;

public class HeaderBodyTrailerFlatRecordNestedType {
    private String kind;
    private Header header = new Header();
    private Body body = new Body();
    private Trailer trailer = new Trailer();

    // Nesting types for naming scope
    // Type name scoped withing enclosed type
    // no relationship between nested type and enclosing type instances
    // static classes nested within classes
    // All classes nested within interface
    // All nested interfaces

    public HeaderBodyTrailerFlatRecordNestedType(String kind, Integer transactionId, String customerName, Integer numItems, String item, Integer quantity, Double cost, Integer totalQuantity, Double totalCost) {
        this.kind = kind;
        header.transactionId = transactionId;
        header.customerName = customerName;
        header.numItems = numItems;
        body.item = item;
        body.quantity = quantity;
        body.cost = cost;
        trailer.totalQuantity = totalQuantity;
        trailer.totalCost = totalCost;
    }

    private static class  Header {
        private Integer transactionId;
        private String  customerName;
        private Integer numItems;

        @Override
        public String toString() {
            return "Header{" +
                    "transactionId=" + transactionId +
                    ", customerName='" + customerName + '\'' +
                    ", numItems=" + numItems +
                    '}';
        }
    }

    private static class Body {
        private String item;
        private Integer quantity;
        private Double cost;

        @Override
        public String toString() {
            return "Body{" +
                    "item='" + item + '\'' +
                    ", quantity=" + quantity +
                    ", cost=" + cost +
                    '}';
        }
    }

    private static class Trailer {
        private Integer totalQuantity;
        private Double totalCost;

        @Override
        public String toString() {
            return "Trailer{" +
                    "totalQuantity=" + totalQuantity +
                    ", totalCost=" + totalCost +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HeaderBodyTrailerFlatRecordNestedType{" +
                "kind='" + kind + '\'' +
                ", header=" + header +
                ", body=" + body +
                ", trailer=" + trailer +
                '}';
    }

}

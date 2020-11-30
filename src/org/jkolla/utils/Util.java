package org.jkolla.utils;

import org.jkolla.models.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Util {
    public Util() {
    }
    public void generateNumbers(){
        Stream.generate(Math::random) // Stream<Double>
                .map(e -> (int) (e * 1000))
                .limit(10)
                .forEach(System.out::println);
    }
    public List<Product> createProducts(){
        return new ArrayList<>(){{
          add(new Product("936","Apple","2020-10-08",Arrays.asList(
                  new ProductDetail("936","805","SweetTango Apples",3.97,2),
                  new ProductDetail("936","475","HoneyCrisp Apples",4.97,3),
                  new ProductDetail("936","877","Organic Gala Apples",5.80,3)
          )));
            add(new Product("979","Pear","2020-10-12",Arrays.asList(
                    new ProductDetail("979","860","Bartlett Pears",3.92,3),
                    new ProductDetail("979","309","Organic Anjou Pears",3.86,2),
                    new ProductDetail("979","597","Bosc Pears",1.38,1)
            )));
            add(new Product("947","Orange","2020-10-15",Arrays.asList(
                    new ProductDetail("947","113","Clementines",4.37,3),
                    new ProductDetail("947","189","Navel Oranges",3.88,3)
            )));
        }};
    }

    public List<FilterOption> createFilterOptions() {
        return new ArrayList<>() {{
            add(new FilterOption("936",Arrays.asList("475","877","475",null)));
            add(new FilterOption("980",Arrays.asList("900","900","empty",null,"null")));
            add(new FilterOption("947",Arrays.asList("189","",null,"null")));
            add(new FilterOption());
        }};
    }
    public List<Movie> createMovies() {
        return new ArrayList<>() {{
            add(new Movie("Rangasthalam", "2018", 8.4, Arrays.asList("Ram Charan", "Samantha Ruth Prabhu", "Aadhi", "Jagapathi Babu")));
            add(new Movie("Bharat Ane Nenu", "2018", 7.7, Arrays.asList("Mahesh Babu", "Kiara Advani", "Prakash Raj")));
            add(new Movie("Saaho", "2019", 5.2, Arrays.asList("Prabhas", "Shraddha Kapoor", "Jackie Shroff")));
            add(new Movie("Bahubali: The Beginning", "2015", 8.1, Arrays.asList("Prabhas", "Rana Daggubati", "Ramya Krishnan")));
            add(new Movie("Baahubali 2: The Conclusion", "2017", 8.2, Arrays.asList("Prabhas", "Rana Daggubati", "Anushka Shetty")));
            add(new Movie("Sye Raa Narasimha Reddy", "2019", 7.5, Arrays.asList("Chiranjeevi", "Amitabh Bachchan", "Sudeep")));
            add(new Movie("Ala Vaikunthapurramuloo", "2020", 7.2, Arrays.asList("Allu Arjun", "Pooja Hegde", "Tabu")));
            add(new Movie("Nannaku Prematho", "2016", 7.6, Arrays.asList("N.T. Rama Rao Jr.", "Rakul Preet Singh", "Jagapathi Babu")));
            add(new Movie("Arjun Reddy", "2017", 8.1, Arrays.asList("Vijay Deverakonda", "Shalini Pandey", "Jia Sharma")));
        }};
    }

    public List<Customer> createCustomers(){
        return new ArrayList<>() {{
            add(new Customer(1600092791,"COURTNEY","DIPAOLA","45379 Squannacook Ter","ITHACA","NY",14853));
            add(new Customer(1600073044,"LUKE","WATKINS","47179 Loiselle Ln","DETROIT","MI",48235));;
            add(new Customer(1600074238,"NICK","SPRAGGS","25761 Roberts St","DALLAS","TX",75239));;
            add(new Customer(1600086354,"JOHN","MURPHY","27187 Dell Hollow Rd","HOUSTON","TX",77048));;
            add(new Customer(1600087382,"JOHN","GLASSFORD","47732 Berkeley Dr","ORLANDO","FL",32836));
            add(new Customer(1600065276,"FRANK","SABORI","27724 Par Ln","MILWAUKEE","WI",53202));
            add(new Customer(1600075741,"LEAH","STERNBERG","9899 Ramblewood Dr","LOS ANGELES","CA",90043));
            add(new Customer(1600084823,"CHRISTIE","ROBERTSON","28039 Lansing Rd","HOUSTON","TX",77054));
            add(new Customer(1600086984,"MICHELLE","MAYTON","15433 East Ave","HOUSTON","TX",77042));
            add(new Customer(1600055711,"KRISTEN","WELLS","4941 Michigan Dr","INDIANAPOLIS","IN",46201));
            add(new Customer(1600063909,"GARY","LAFFERTY","48279 Hills Ct","EL PASO","TX",79924));
            add(new Customer(1600065060,"MARIA","ZAMORA","28070 Whippoorwill Ln","ITHACA","NY",14853));
            add(new Customer(1600066473,"CARL","THOMPSON","47073 Swan Ave","SPRINGFIELD","MA",1119));
            add(new Customer(1600071693,"RICHARD","MCRAE","16921 Emmet Pl","SPRINGFIELD","MA",1105));
            add(new Customer(1600075664,"MICHAEL","LAXSON","6489 Burnside St","MADISON","WI",53703));
            add(new Customer(1600078278,"ARRON","MONROE","47143 Fairmont Ct","HOUSTON","TX",77091));
            add(new Customer(1600079475,"JIMMY","HARRISON","40145 Georgia Rd","GALVESTON","TX",77551));
            add(new Customer(1600085768,"TERESA","MCGINNIS","4346 Winterberry Way","AUSTIN","TX",78757));
            add(new Customer(1600088462,"GAYNELLE","LEA","8689 Tappan St","SAINT LOUIS","MO",63106));
            add(new Customer(1600090810,"RONALD","CHASE","5290 Porter St","NEW YORK","NY",10023));
        }};
    }

    public List<StoreInfo> createStoreInfo() {
        return new ArrayList<StoreInfo>() {{
            add(new StoreInfo(1101,"4867 Stephanie Cir       ","BAKERSFIELD              ","CA","93311","JEFFERY SCHAUBERT        ","                         ","                         "));
            add(new StoreInfo(1102,"2250 Lee Rd              ","BAKERSFIELD              ","CA","93306","MARY ZARATE              ","AGUSTIN COTTON           ","                         "));
            add(new StoreInfo(1103,"2661 H St                ","FRESNO                   ","CA","93726","EDWARD MCGIVNEY          ","                         ","                         "));
            add(new StoreInfo(1104,"3416 Pine Grove Rd       ","FRESNO                   ","CA","93701","DEBORAH DURETT           ","MICHAEL ALLEN            ","                         "));
            add(new StoreInfo(1105,"2046 Bradbury St         ","FRESNO                   ","CA","93711","RUBY CAREY               ","                         ","                         "));
            add(new StoreInfo(1106,"4065 Randolph Pl         ","LOS ANGELES              ","CA","90019","RUBY CAREY               ","                         ","                         "));
            add(new StoreInfo(1107,"325 Jackson Ter          ","LOS ANGELES              ","CA","90008","KENNETH FOOS             ","                         ","                         "));
            add(new StoreInfo(1108,"181 Country Club Cir     ","LOS ANGELES              ","CA","90024","BOBBIE SWAN              ","                         ","                         "));
            add(new StoreInfo(1109,"4990 Oriole Ave          ","LOS ANGELES              ","CA","90027","JENNIFER RILEY           ","                         ","                         "));
            add(new StoreInfo(1110,"798 Laurelwood Dr        ","LOS ANGELES              ","CA","90077","TED CODY                 ","                         ","                         "));
            add(new StoreInfo(1111,"3704 Magnulia Dr         ","LOS ANGELES              ","CA","90068","DEBORAH DURETT           ","HELEN WHITE              ","DONALD BADER             "));
            add(new StoreInfo(1112,"1394 Bello Ln            ","LOS ANGELES              ","CA","90064","JOY WILLIAMS             ","JERRY COOLEY             ","                         "));
        }};
    }

    public List<CustomerTransaction> createCustomerTransaction(){
        return new ArrayList<CustomerTransaction>(){{
            add(new CustomerTransaction ("C008231", LocalDate.parse("1993.10.22" , DateTimeFormatter.ofPattern("yyyy.MM.dd")),122.00));
            add(new CustomerTransaction ("C002142", LocalDate.parse("1994.06.22" , DateTimeFormatter.ofPattern("yyyy.MM.dd")),22.25));
            add(new CustomerTransaction ("C003213", LocalDate.parse("1993.02.12" , DateTimeFormatter.ofPattern("yyyy.MM.dd")),47.95));
            add(new CustomerTransaction ("C003213", LocalDate.parse("1995.12.11" , DateTimeFormatter.ofPattern("yyyy.MM.dd")),17.42));
            add(new CustomerTransaction ("C002142", LocalDate.parse("1994.03.23" , DateTimeFormatter.ofPattern("yyyy.MM.dd")),52.20));
            add(new CustomerTransaction ("C003213", LocalDate.parse("1994.11.05" , DateTimeFormatter.ofPattern("yyyy.MM.dd")),221.24));
            add(new CustomerTransaction ("C004221", LocalDate.parse("1994.08.15" , DateTimeFormatter.ofPattern("yyyy.MM.dd")),25.25));
            add(new CustomerTransaction ("C008231", LocalDate.parse("1995.12.10" , DateTimeFormatter.ofPattern("yyyy.MM.dd")),52.10));
        }};
    }
}

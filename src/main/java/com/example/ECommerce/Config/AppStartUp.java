package com.example.ECommerce.Config;

import com.example.ECommerce.Entities.*;
import com.example.ECommerce.Entities.SubEntities.Customer;
import com.example.ECommerce.Entities.SubEntities.Seller;
import com.example.ECommerce.Enums.Categories;
import com.example.ECommerce.Enums.Country;
import com.example.ECommerce.Enums.Report_Category;
import com.example.ECommerce.Enums.Roles;
import com.example.ECommerce.Repositories.JPA.RoleBasedRepositories.*;
import com.example.ECommerce.Repositories.Mongo.OrderRepository;
import com.example.ECommerce.Repositories.Mongo.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

public class AppStartUp implements CommandLineRunner {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final OfferRepository offerRepository;
    private final PaymentRepository paymentRepository;
    private final ReportRepository reportRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;
    private PasswordEncoder passwordEncoder;

    public AppStartUp(UserRepository userRepository, CustomerRepository customerRepository,
                      SellerRepository sellerRepository, AddressRepository addressRepository,
                      ProductRepository productRepository, OfferRepository offerRepository,
                      PaymentRepository paymentRepository, ReportRepository reportRepository, BCryptPasswordEncoder passwordEncoder,
                      OrderRepository orderRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
        this.offerRepository = offerRepository;
        this.paymentRepository = paymentRepository;
        this.reportRepository = reportRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderRepository = orderRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //clearData();

        // Create Addresses
        Address customer1Address = createAddress("15 Mohamed Mahmoud St", "Apt 5", "Cairo", "Cairo", Country.EGYPT, "11511");
        Address customer2Address = createAddress("Akasaka 9-7-1", "Floor 12", "Tokyo", "Kanto", Country.JAPAN, "107-0052");
        Address customer3Address = createAddress("Rua Oscar Freire 800", null, "São Paulo", "SP", Country.BRAZIL, "01426-001");
        Address customer4Address = createAddress("Orchard Road 391", "Unit 03-01", "Singapore", null, Country.SINGAPORE, "238873");
        Address customer5Address = createAddress("Lombard St 1200", null, "San Francisco", "CA", Country.UNITED_STATES, "94109");
        Address customer6Address = createAddress("Königsallee 27", null, "Düsseldorf", "NRW", Country.GERMANY, "40212");
        Address customer7Address = createAddress("Oxford Street 172", "Flat 8", "London", null, Country.UNITED_KINGDOM, "W1D 1NN");
        Address customer8Address = createAddress("Banjeong-ro 262", "3rd Floor", "Seongnam", "Gyeonggi", Country.SOUTH_KOREA, "13486");
        Address customer9Address = createAddress("Collins St 401", "Tower 2", "Melbourne", "VIC", Country.AUSTRALIA, "3000");
        Address customer10Address = createAddress("Khao San Rd 102", null, "Bangkok", null, Country.THAILAND, "10200");
        Address customer11Address = createAddress("Paseo de la Reforma 222", "Apt 9B", "Mexico City", "CDMX", Country.MEXICO, "06500");
        Address customer12Address = createAddress("Arbat St 52", null, "Moscow", null, Country.RUSSIA, "119002");
        Address customer13Address = createAddress("Jinli Street 33", null, "Chengdu", "Sichuan", Country.CHINA, "610041");
        Address customer14Address = createAddress("Khalid bin Al Waleed Rd", "Tower C", "Dubai", null, Country.SAUDI_ARABIA, "00000");
        Address customer15Address = createAddress("Rue de Rivoli 112", null, "Paris", "Île-de-France", Country.FRANCE, "75001");
        Address customer16Address = createAddress("Campos Eliseos 345", "PH", "Mexico City", "CDMX", Country.MEXICO, "11560");
        Address customer17Address = createAddress("Bourke St 500", null, "Melbourne", "VIC", Country.AUSTRALIA, "3000");
        Address customer18Address = createAddress("Via del Corso 200", null, "Rome", "Lazio", Country.ITALY, "00186");
        Address customer19Address = createAddress("Kruisstraat 81", null, "Eindhoven", "NB", Country.NETHERLANDS, "5612 CJ");
        Address customer20Address = createAddress("Sveavägen 65", "Lgh 1203", "Stockholm", null, Country.SWEDEN, "113 50");
        Address customer21Address = createAddress("Istiklal Cd. No:124", null, "Istanbul", null, Country.TURKEY, "34433");
        Address customer22Address = createAddress("Elm Street 1428", null, "Johannesburg", "Gauteng", Country.SOUTH_AFRICA, "2001");
        Address customer23Address = createAddress("Robson St 1060", "Suite 405", "Vancouver", "BC", Country.CANADA, "V6E 1B5");
        Address customer24Address = createAddress("Avenida Paulista 1578", null, "São Paulo", "SP", Country.BRAZIL, "01310-200");
        Address customer25Address = createAddress("Nguyen Hue St 72", "Floor 7", "Ho Chi Minh City", null, Country.VIETNAM, "70000");
        Address customer26Address = createAddress("Jalan Tun Razak 109", null, "Kuala Lumpur", null, Country.MALAYSIA, "50400");
        Address customer27Address = createAddress("Kloof Street 103", null, "Cape Town", "WC", Country.SOUTH_AFRICA, "8001");
        Address customer28Address = createAddress("Potsdamer Platz 5", null, "Berlin", null, Country.GERMANY, "10785");
        Address customer29Address = createAddress("Ginza 4-6-16", "Chuo-ku", "Tokyo", "Kanto", Country.JAPAN, "104-0061");
        Address customer30Address = createAddress("Fifth Avenue 667", "Apt 3202", "New York", "NY", Country.UNITED_STATES, "10022");
        Address customer31Address = createAddress("La Rambla 98", "3ºA", "Barcelona", "Catalonia", Country.SPAIN, "08002");
        Address customer32Address = createAddress("Bahnhofstrasse 45", null, "Zurich", "ZH", Country.SWITZERLAND, "8001");
        Address customer33Address = createAddress("Sheikh Zayed Road 1", "Tower 22", "Abu Dhabi", null, Country.UNITED_ARAB_EMIRATES, "00000");
        Address customer34Address = createAddress("MM Alam Road 24", null, "Lahore", "Punjab", Country.PAKISTAN, "54000");
        Address customer35Address = createAddress("Queen Street 291", "Apt 501", "Auckland", null, Country.NEW_ZEALAND, "1010");
        Address customer36Address = createAddress("Nathan Road 188", "Floor 15", "Hong Kong", null, Country.HONG_KONG, "00000");
        Address customer37Address = createAddress("Marine Drive 44", null, "Colombo", "Western", Country.SRI_LANKA, "00300");
        Address customer38Address = createAddress("Gulshan Avenue 56", "Block C", "Dhaka", null, Country.BANGLADESH, "1212");
        Address customer39Address = createAddress("Karl Johans gate 22", null, "Oslo", "Oslo", Country.NORWAY, "0159");
        Address customer40Address = createAddress("Váci út 67", "Lépcsőház 3", "Budapest", null, Country.HUNGARY, "1056");
        Address customer41Address = createAddress("Ermou 15", "2nd Floor", "Athens", "Attica", Country.GREECE, "10563");
        Address customer42Address = createAddress("Nowy Świat 44", null, "Warsaw", "Mazovia", Country.POLAND, "00-001");
        Address customer43Address = createAddress("Wenceslas Square 28", null, "Prague", "Bohemia", Country.CZECH_REPUBLIC, "11000");
        Address customer44Address = createAddress("Kiseleff Road 12", null, "Bucharest", "Bucharest", Country.ROMANIA, "011347");
        Address customer45Address = createAddress("Stradun 31", null, "Dubrovnik", "Dubrovnik-Neretva", Country.CROATIA, "20000");
        Address customer46Address = createAddress("Knez Mihailova 16", null, "Belgrade", null, Country.SERBIA, "11000");
        Address customer47Address = createAddress("Bulevardi i Ri 7", null, "Tirana", null, Country.ALBANIA, "1001");
        Address customer48Address = createAddress("Füvészkert u. 8", null, "Budapest", "Central Hungary", Country.HUNGARY, "1087");
        Address customer49Address = createAddress("Nurzhol Boulevard 15", null, "Astana", null, Country.KAZAKHSTAN, "010000");
        Address customer50Address = createAddress("Khreshchatyk 25", "Apt 44", "Kyiv", null, Country.UKRAINE, "01001");
        Address customer51Address = createAddress("Gedimino pr. 27", null, "Vilnius", null, Country.LITHUANIA, "01104");
        Address customer52Address = createAddress("Brīvības iela 33", null, "Riga", null, Country.LATVIA, "LV-1010");
        Address customer53Address = createAddress("Prospect Nezalezhnosti 11", null, "Minsk", null, Country.BELARUS, "220030");
        Address customer54Address = createAddress("Tsimiski 58", null, "Thessaloniki", "Central Macedonia", Country.GREECE, "54624");
        Address customer55Address = createAddress("Cihangir Sk. No:12", "D:5", "Izmir", "Aegean", Country.TURKEY, "35260");
        Address customer56Address = createAddress("789 Park Ave", "Apt 12C", "New York", "NY", Country.UNITED_STATES, "10022");
        Address customer57Address = createAddress("1010 Wilshire Blvd", "Floor 25", "Los Angeles", "CA", Country.UNITED_STATES, "90017");
        Address customer58Address = createAddress("200 Magnificent Mile", "Suite 800", "Chicago", "IL", Country.UNITED_STATES, "60611");
        Address customer59Address = createAddress("221B Baker St", null, "London", null, Country.UNITED_KINGDOM, "NW1 6XE");
        Address customer60Address = createAddress("15 Princes St", "Flat 3B", "Edinburgh", "Scotland", Country.UNITED_KINGDOM, "EH2 2ER");
        Address customer61Address = createAddress("Kurfürstendamm 220", null, "Berlin", "Berlin", Country.GERMANY, "10719");
        Address customer62Address = createAddress("Marienplatz 1", "Apt 5", "Munich", "Bavaria", Country.GERMANY, "80331");
        Address customer63Address = createAddress("72 Champs-Élysées", null, "Paris", "Île-de-France", Country.FRANCE, "75008");
        Address customer64Address = createAddress("Vieux Port", "Résidence Le Panier", "Marseille", "Provence", Country.FRANCE, "13001");
        Address customer65Address = createAddress("100 Queen St W", "Unit 1502", "Toronto", "ON", Country.CANADA, "M5H 2N2");
        Address customer66Address = createAddress("999 Canada Place", "Apt 320", "Vancouver", "BC", Country.CANADA, "V6C 3E1");
        Address customer67Address = createAddress("3-5-1 Marunouchi", "Chiyoda-ku", "Tokyo", "Kanto", Country.JAPAN, "100-0005");
        Address customer68Address = createAddress("1-5-55 Umeda", "Kita-ku", "Osaka", "Kansai", Country.JAPAN, "530-0001");
        Address customer69Address = createAddress("Gangnam-daero 132", "Gangnam-gu", "Seoul", null, Country.SOUTH_KOREA, "06220");
        Address customer70Address = createAddress("Haeundae Beach Rd 11", "Haeundae-gu", "Busan", null, Country.SOUTH_KOREA, "48058");

        //Seller Addresses
        Address seller1Address = createAddress("Silicon Valley Blvd 1000", "Building C", "San Jose", "CA", Country.UNITED_STATES, "95134");
        Address shipping1Address = createAddress("Logistics Park Rd 2500", "Warehouse 12", "Memphis", "TN", Country.UNITED_STATES, "38118");

        Address seller2Address = createAddress("Electronicaweg 71", null, "Amsterdam", "NH", Country.NETHERLANDS, "1014 BG");
        Address shipping2Address = createAddress("DistriPark 33", null, "Rotterdam", "ZH", Country.NETHERLANDS, "3197 LM");

        Address seller3Address = createAddress("Shennan Blvd 5001", "Tower A", "Shenzhen", "Guangdong", Country.CHINA, "518048");
        Address shipping3Address = createAddress("Baoan Dist. Logistics Center", "Zone 3", "Shenzhen", "Guangdong", Country.CHINA, "518103");

        Address seller4Address = createAddress("Dubai Design Dist.", "Building 7", "Dubai", null, Country.SAUDI_ARABIA, "00000");
        Address shipping4Address = createAddress("Jebel Ali Free Zone", "Shed 12", "Dubai", null, Country.SAUDI_ARABIA, "00000");

        Address seller5Address = createAddress("Bang Na-Trat Rd 18", "Bitec Tower", "Bangkok", null, Country.THAILAND, "10260");
        Address shipping5Address = createAddress("Laem Chabang Port", "Terminal B", "Chon Buri", null, Country.THAILAND, "20130");

        Address seller6Address = createAddress("Paseo de Gracia 56", null, "Barcelona", "Catalonia", Country.SPAIN, "08007");
        Address shipping6Address = createAddress("Polígono Industrial Sur", "Nave 8", "Hospitalet", "Catalonia", Country.SPAIN, "08907");


        Address seller7Address = createAddress("Rue du Rhône 120", null, "Geneva", "GE", Country.SWITZERLAND, "1204");
        Address shipping7Address = createAddress("Chemin des Tourelles 4", null, "Plan-les-Ouates", "GE", Country.SWITZERLAND, "1228");


        Address seller8Address = createAddress("Al Maryah Island", "ADGM Square", "Abu Dhabi", null, Country.UNITED_ARAB_EMIRATES, "00000");
        Address shipping8Address = createAddress("KIZAD Logistics Park", "Zone A", "Abu Dhabi", null, Country.UNITED_ARAB_EMIRATES, "00000");


        Address seller9Address = createAddress("I.I. Chundrigar Road 45", "Textile Tower", "Karachi", "Sindh", Country.PAKISTAN, "74000");
        Address shipping9Address = createAddress("Port Qasim", "Terminal 3", "Karachi", "Sindh", Country.PAKISTAN, "75010");


        Address seller10Address = createAddress("Fanshawe Street 136", "Wynyard Quarter", "Auckland", null, Country.NEW_ZEALAND, "1010");
        Address shipping10Address = createAddress("East Tamaki Hub", "Building 5", "Auckland", null, Country.NEW_ZEALAND, "2013");

        Address seller11Address = createAddress("Industrial Zone of Kalamata", "Block 7", "Kalamata", "Peloponnese", Country.GREECE, "24100");
        Address shipping11Address = createAddress("Port of Piraeus", "Terminal C", "Piraeus", "Attica", Country.GREECE, "18538");


        Address seller12Address = createAddress("Jagiellońska 76", "TechPark Warsaw", "Warsaw", "Mazovia", Country.POLAND, "03-301");
        Address shipping12Address = createAddress("Logistics Center Łódź", "Hala 12", "Łódź", "Łódź Voivodeship", Country.POLAND, "93-421");


        Address seller13Address = createAddress("Avia Dvorakova 16", null, "Mladá Boleslav", "Central Bohemia", Country.CZECH_REPUBLIC, "29301");
        Address shipping13Address = createAddress("Industrial Zone Ostrava", "Sector D", "Ostrava", "Moravian-Silesian", Country.CZECH_REPUBLIC, "70200");


        Address seller14Address = createAddress("Calea Victoriei 155", "Carpathian Woodworks", "Brasov", "Brasov", Country.ROMANIA, "500068");
        Address shipping14Address = createAddress("Industrial Park Cluj", "Warehouse 9", "Cluj-Napoca", "Cluj", Country.ROMANIA, "407057");


        Address seller15Address = createAddress("Put Plovanije 12", null, "Split", "Split-Dalmatia", Country.CROATIA, "21000");
        Address shipping15Address = createAddress("Port of Rijeka", "Cold Storage 3", "Rijeka", "Primorje-Gorski", Country.CROATIA, "51000");


        Address seller16Address = createAddress("Bulevar Mihajla Pupina 10", "Tech Tower", "Novi Sad", "Vojvodina", Country.SERBIA, "21000");
        Address shipping16Address = createAddress("Indjija Industrial Zone", "Sector B2", "Indjija", "Vojvodina", Country.SERBIA, "22320");


        Address seller17Address = createAddress("Rruga e Durresit 45", null, "Tirana", null, Country.ALBANIA, "1001");
        Address shipping17Address = createAddress("Port of Durres", "Terminal South", "Durres", null, Country.ALBANIA, "2000");


        Address seller18Address = createAddress("Turan Ave 34", "Energy Complex", "Atyrau", null, Country.KAZAKHSTAN, "060002");
        Address shipping18Address = createAddress("Caspian Port", "Zone 4", "Aktau", null, Country.KAZAKHSTAN, "130000");


        Address seller19Address = createAddress("Mykolaivska 15", "Grain Terminal", "Odessa", "Odessa Oblast", Country.UKRAINE, "65000");
        Address shipping19Address = createAddress("Port of Chornomorsk", "Silo Complex", "Chornomorsk", "Odessa Oblast", Country.UKRAINE, "68000");


        Address seller20Address = createAddress("Valnu iela 30", "TechHub Riga", "Riga", null, Country.LATVIA, "LV-1050");
        Address shipping20Address = createAddress("Riga Free Port", "Warehouse 7A", "Riga", null, Country.LATVIA, "LV-1048");

        Address seller21Address = createAddress("1 Infinite Loop", null, "Cupertino", "CA", Country.UNITED_STATES, "95014");
        Address shipping21Address = createAddress("900 Dubuque Ave", "Distribution Center 5", "San Jose", "CA", Country.UNITED_STATES, "95134");

        Address seller22Address = createAddress("1 Poultry", "Floor 22", "London", null, Country.UNITED_KINGDOM, "EC2R 8EJ");
        Address shipping22Address = createAddress("Thamesport Terminal", "Unit 12", "Gravesend", "Kent", Country.UNITED_KINGDOM, "DA11 0BG");

        Address seller23Address = createAddress("Auto-Union-Ring 1", null, "Ingolstadt", "Bavaria", Country.GERMANY, "85045");
        Address shipping23Address = createAddress("Port of Bremerhaven", "Terminal 4", "Bremerhaven", "Bremen", Country.GERMANY, "27568");

        Address seller24Address = createAddress("24 Rue du Faubourg Saint-Honoré", null, "Paris", "Île-de-France", Country.FRANCE, "75008");
        Address shipping24Address = createAddress("Logistique Seine", "Zone A", "Le Havre", "Normandy", Country.FRANCE, "76600");

        Address seller25Address = createAddress("500 4th Ave SW", "Tower 2", "Calgary", "AB", Country.CANADA, "T2P 2V8");
        Address shipping25Address = createAddress("Port of Vancouver", "Terminal 3", "Vancouver", "BC", Country.CANADA, "V6C 3E1");

        Address seller26Address = createAddress("6-1-1 Osaki", "Shinagawa-ku", "Tokyo", "Kanto", Country.JAPAN, "141-0032");
        Address shipping26Address = createAddress("Port of Yokohama", "Warehouse 7", "Yokohama", "Kanagawa", Country.JAPAN, "220-0012");

        Address seller27Address = createAddress("129 Teheran-ro", "Gangnam-gu", "Seoul", null, Country.SOUTH_KOREA, "06133");
        Address shipping27Address = createAddress("Busan New Port", "Terminal C", "Busan", null, Country.SOUTH_KOREA, "49111");

        Address seller28Address = createAddress("350 5th Ave", "Floor 68", "New York", "NY", Country.UNITED_STATES, "10118");
        Address shipping28Address = createAddress("JFK Logistics Park", "Building 12", "Queens", "NY", Country.UNITED_STATES, "11430");
        //Create Customer
        Customer customer1 = createCustomer("ahmed_hassan", "ahmed.hassan@eg.example.com", passwordEncoder.encode("Egypt123!"), customer1Address);
        Customer customer2 = createCustomer("yamada_hiroshi", "hiroshi.y@jp.example.com", passwordEncoder.encode("TokyoPass1!"), customer2Address);
        Customer customer3 = createCustomer("joao_souza", "joao.souza@br.example.com", passwordEncoder.encode("SaoPaulo123!"), customer3Address);
        Customer customer4 = createCustomer("lim_jiak", "lim.jiak@sg.example.com", passwordEncoder.encode("Singapore123!"), customer4Address);
        Customer customer5 = createCustomer("michael_johnson", "m.johnson@us.example.com", passwordEncoder.encode("SFtech123!"), customer5Address);
        Customer customer6 = createCustomer("hans_mueller", "h.mueller@de.example.com", passwordEncoder.encode("Duesseldorf1!"), customer6Address);
        Customer customer7 = createCustomer("emily_wilson", "e.wilson@uk.example.com", passwordEncoder.encode("LondonPass1!"), customer7Address);
        Customer customer8 = createCustomer("lee_minho", "minho.lee@kr.example.com", passwordEncoder.encode("Seoul123!"), customer8Address);
        Customer customer9 = createCustomer("liam_wilson", "liam.w@au.example.com", passwordEncoder.encode("Melbourne1!"), customer9Address);
        Customer customer10 = createCustomer("somchai_sukjai", "somchai.sukjai@th.example.com", passwordEncoder.encode("Bangkok123!"), customer10Address);
        Customer customer11 = createCustomer("sofia_hernandez", "s.hernandez@mx.example.com", passwordEncoder.encode("MexicoCity1!"), customer11Address);
        Customer customer12 = createCustomer("dmitry_ivanov", "d.ivanov@ru.example.com", passwordEncoder.encode("Moscow123!"), customer12Address);
        Customer customer13 = createCustomer("zhang_wei", "wei.zhang@cn.example.com", passwordEncoder.encode("Chengdu456!"), customer13Address);
        Customer customer14 = createCustomer("khalid_al_maktoum", "khalid.almaktoum@ae.example.com", passwordEncoder.encode("Dubai456!"), customer14Address);
        Customer customer15 = createCustomer("marie_dupont", "m.dupont@fr.example.com", passwordEncoder.encode("Paris789!"), customer15Address);
        Customer customer16 = createCustomer("juan_perez", "juan.perez@mx.example.com", passwordEncoder.encode("MexicoCity456!"), customer16Address);
        Customer customer17 = createCustomer("charlotte_smith", "c.smith@au.example.com", passwordEncoder.encode("Melbourne789!"), customer17Address);
        Customer customer18 = createCustomer("giovanni_rossi", "giovanni.rossi@it.example.com", passwordEncoder.encode("Rome123!"), customer18Address);
        Customer customer19 = createCustomer("viktor_ivanov", "v.ivanov@nl.example.com", passwordEncoder.encode("Eindhoven123!"), customer19Address);
        Customer customer20 = createCustomer("lars_andersson", "l.andersson@se.example.com", passwordEncoder.encode("Stockholm456!"), customer20Address);
        Customer customer21 = createCustomer("mehmet_yilmaz", "m.yilmaz@tr.example.com", passwordEncoder.encode("Istanbul456!"), customer21Address);
        Customer customer22 = createCustomer("nomsa_mbatha", "n.mbatha@za.example.com", passwordEncoder.encode("Joburg789!"), customer22Address);
        Customer customer23 = createCustomer("liam_tremblay", "l.tremblay@ca.example.com", passwordEncoder.encode("VanPass123!"), customer23Address);
        Customer customer24 = createCustomer("isabella_silva", "i.silva@br.example.com", passwordEncoder.encode("SaoPaulo456!"), customer24Address);
        Customer customer25 = createCustomer("nguyen_van_a", "v.nguyen@vn.example.com", passwordEncoder.encode("HCMCity123!"), customer25Address);
        Customer customer26 = createCustomer("tan_wei_ling", "tan.wei@my.example.com", passwordEncoder.encode("KL456!"), customer26Address);
        Customer customer27 = createCustomer("sipho_mthembu", "sipho.mthembu@za.example.com", passwordEncoder.encode("CapeTown123!"), customer27Address);
        Customer customer28 = createCustomer("anna_beck", "a.beck@de.example.com", passwordEncoder.encode("Berlin789!"), customer28Address);
        Customer customer29 = createCustomer("takashi_yamamoto", "takashi.yamamoto@jp.example.com", passwordEncoder.encode("Tokyo456!"), customer29Address);
        Customer customer30 = createCustomer("emma_williams", "e.williams@us.example.com", passwordEncoder.encode("NYC789!"), customer30Address);
        Customer customer31 = createCustomer("carlos_garcia", "c.garcia@es.example.com", passwordEncoder.encode("Barcelona123!"), customer31Address);
        Customer customer32 = createCustomer("sofia_muller", "s.muller@ch.example.com", passwordEncoder.encode("Zurich456!"), customer32Address);
        Customer customer33 = createCustomer("ahmed_al_farsi", "ahmed.al.farsi@ae.example.com", passwordEncoder.encode("AbuDhabi123!"), customer33Address);
        Customer customer34 = createCustomer("ali_rahman", "a.rahman@pk.example.com", passwordEncoder.encode("Lahore789!"), customer34Address);
        Customer customer35 = createCustomer("lucas_brown", "l.brown@nz.example.com", passwordEncoder.encode("Auckland123!"), customer35Address);
        Customer customer36 = createCustomer("choi_man", "choi.man@hk.example.com", passwordEncoder.encode("HongKong123!"), customer36Address);
        Customer customer37 = createCustomer("mohamed_ali", "m.ali@lk.example.com", passwordEncoder.encode("Colombo123!"), customer37Address);
        Customer customer38 = createCustomer("fatima_begum", "f.begum@bd.example.com", passwordEncoder.encode("Dhaka789!"), customer38Address);
        Customer customer39 = createCustomer("olav_hansen", "olav.hansen@no.example.com", passwordEncoder.encode("Oslo123!"), customer39Address);
        Customer customer40 = createCustomer("novak_jovanovic", "n.jovanovic@rs.example.com", passwordEncoder.encode("Belgrade123!"), customer40Address);
        Customer customer41 = createCustomer("dimitris_papadopoulos", "d.papa@gr.example.com", passwordEncoder.encode("Athens789!"), customer41Address);
        Customer customer42 = createCustomer("jan_kowalski", "j.kowalski@pl.example.com", passwordEncoder.encode("Warsaw123!"), customer42Address);
        Customer customer43 = createCustomer("petr_novak", "p.novak@cz.example.com", passwordEncoder.encode("Prague456!"), customer43Address);
        Customer customer44 = createCustomer("ion_popescu", "i.popescu@ro.example.com", passwordEncoder.encode("Bucharest789!"), customer44Address);
        Customer customer45 = createCustomer("marko_horvat", "m.horvat@hr.example.com", passwordEncoder.encode("Dubrovnik123!"), customer45Address);
        Customer customer46 = createCustomer("marko_petrović", "marko.petrovic@rs.example.com", passwordEncoder.encode("Belgrade456!"), customer46Address);
        Customer customer47 = createCustomer("ardiana_bega", "ardiana.bega@al.example.com", passwordEncoder.encode("Tirana123!"), customer47Address);
        Customer customer48 = createCustomer("ferenc_nagy", "ferenc.nagy@hu.example.com", passwordEncoder.encode("Budapest123!"), customer48Address);
        Customer customer49 = createCustomer("aruzhan_kazakh", "a.kazakh@kz.example.com", passwordEncoder.encode("Astana789!"), customer49Address);
        Customer customer50 = createCustomer("oleksiy_petrenko", "o.petrenko@ua.example.com", passwordEncoder.encode("Kyiv123!"), customer50Address);
        Customer customer51 = createCustomer("jonas_kazlauskas", "jonas.kazlauskas@lt.example.com", passwordEncoder.encode("Vilnius123!"), customer51Address);
        Customer customer52 = createCustomer("andrejs_kalnins", "andrejs.kalnins@lv.example.com", passwordEncoder.encode("Riga123!"), customer52Address);
        Customer customer53 = createCustomer("aleksandr_ivanov", "aleksandr.ivanov@by.example.com", passwordEncoder.encode("Minsk123!"), customer53Address);
        Customer customer54 = createCustomer("nikos_papadakis", "n.papa@gr.example.com", passwordEncoder.encode("Thessaloniki456!"), customer54Address);
        Customer customer55 = createCustomer("mustafa_ozdemir", "mustafa.ozdemir@tr.example.com", passwordEncoder.encode("Istanbul123!"), customer55Address);
        Customer customer56 = createCustomer("ethan_smith", "e.smith@us.example.com", passwordEncoder.encode("Chicago123!"), customer56Address);
        Customer customer57 = createCustomer("john_smith", "john.smith@us.example.com", passwordEncoder.encode("LA123!"), customer57Address);
        Customer customer58 = createCustomer("james_johnson", "james.johnson@us.example.com", passwordEncoder.encode("Chicago456!"), customer58Address);
        Customer customer59 = createCustomer("harry_jones", "harry.jones@uk.example.com", passwordEncoder.encode("London123!"), customer59Address);
        Customer customer60 = createCustomer("angus_macleod", "angus.macleod@uk.example.com", passwordEncoder.encode("Edinburgh123!"), customer60Address);
        Customer customer61 = createCustomer("lukas_schneider", "lukas.schneider@de.example.com", passwordEncoder.encode("Berlin456!"), customer61Address);
        Customer customer62 = createCustomer("felix_mueller", "felix.mueller@de.example.com", passwordEncoder.encode("Munich123!"), customer62Address);
        Customer customer63 = createCustomer("jean_dupont", "jean.dupont@fr.example.com", passwordEncoder.encode("Paris123!"), customer63Address);
        Customer customer64 = createCustomer("pierre_leclerc", "pierre.leclerc@fr.example.com", passwordEncoder.encode("Marseille123!"), customer64Address);
        Customer customer65 = createCustomer("olivia_martin", "o.martin@ca.example.com", passwordEncoder.encode("Toronto456!"), customer65Address);
        Customer customer66 = createCustomer("noah_tremblay", "noah.tremblay@ca.example.com", passwordEncoder.encode("Vancouver123!"), customer66Address);
        Customer customer67 = createCustomer("kenji_tanaka", "kenji.tanaka@jp.example.com", passwordEncoder.encode("Tokyo789!"), customer67Address);
        Customer customer68 = createCustomer("hiroshi_sato", "hiroshi.sato@jp.example.com", passwordEncoder.encode("Osaka123!"), customer68Address);
        Customer customer69 = createCustomer("min_ji", "min.ji@kr.example.com", passwordEncoder.encode("Seoul123!"), customer69Address);
        Customer customer70 = createCustomer("jung_hoon", "jung.hoon@kr.example.com", passwordEncoder.encode("Busan123!"), customer70Address);


        // Create Seller
        // Create Sellers
        Seller seller1 = createSeller("tech_4", "seller@example.com", passwordEncoder.encode("sellerpass"), seller1Address, "Tech Corp", "TAX-12345", shipping1Address);
        Seller seller2 = createSeller("future_fashion", "fashion@example.com", passwordEncoder.encode("fashionpass"), seller2Address, "Fashion Inc", "TAX-54321", shipping2Address);
        Seller seller3 = createSeller("books_world", "books@example.com", passwordEncoder.encode("bookspass"), seller3Address, "Bookstore", "TAX-12345", shipping3Address);
        Seller seller4 = createSeller("dxb_design", "contact@albanoordesign.com", passwordEncoder.encode("sellerpass"), seller4Address, "Al Banoor Design", "SA-456789", shipping4Address);
        Seller seller5 = createSeller("bangkok_electro", "info@electrothai.com", passwordEncoder.encode("sellerpass"), seller5Address, "Electro Thai Co.", "TH-102345", shipping5Address);
        Seller seller6 = createSeller("barcelona_fashions", "contact@barcelonafashions.com", passwordEncoder.encode("sellerpass"), seller6Address, "Barcelona Fashions SL", "ES-BAR-08007", shipping6Address);
        Seller seller7 = createSeller("geneva_artisan", "info@genevaartisan.ch", passwordEncoder.encode("sellerpass"), seller7Address, "Geneva Artisan Ltd", "CH-GEN-1204", shipping7Address);
        Seller seller8 = createSeller("abudhabi_traders", "sales@abudhabi-traders.ae", passwordEncoder.encode("sellerpass"), seller8Address, "Abu Dhabi Traders LLC", "AE-987654", shipping8Address);
        Seller seller9 = createSeller("karachi_textiles", "contact@karachitextiles.pk", passwordEncoder.encode("sellerpass"), seller9Address, "Karachi Textiles Pvt Ltd", "PK-74000", shipping9Address);
        Seller seller10 = createSeller("auckland_imports", "info@aucklandimports.co.nz", passwordEncoder.encode("sellerpass"), seller10Address, "Auckland Imports Ltd", "NZ-AU-1010", shipping10Address);
        Seller seller11 = createSeller("kalamata_foods", "sales@kalamatafoods.gr", passwordEncoder.encode("sellerpass"), seller11Address, "Kalamata Foods S.A.", "GR-24100", shipping11Address);
        Seller seller12 = createSeller("warsaw_tech", "contact@warsawtech.pl", passwordEncoder.encode("sellerpass"), seller12Address, "Warsaw Tech Sp. z o.o.", "PL-03301", shipping12Address);
        Seller seller13 = createSeller("bohemia_auto", "info@bohemiaauto.cz", passwordEncoder.encode("sellerpass"), seller13Address, "Bohemia Auto s.r.o.", "CZ-29301", shipping13Address);
        Seller seller14 = createSeller("brasov_wood", "contact@brasovwood.ro", passwordEncoder.encode("sellerpass"), seller14Address, "Brasov Wood Industries SRL", "RO-500068", shipping14Address);
        Seller seller15 = createSeller("split_marine", "info@splitmarine.hr", passwordEncoder.encode("sellerpass"), seller15Address, "Split Marine Logistics d.o.o.", "HR-21000", shipping15Address);
        Seller seller16 = createSeller("novisad_it", "sales@novisadit.rs", passwordEncoder.encode("sellerpass"), seller16Address, "Novi Sad IT Solutions", "RS-21000", shipping16Address);
        Seller seller17 = createSeller("tirana_trades", "contact@tiranatrades.al", passwordEncoder.encode("sellerpass"), seller17Address, "Tirana Trades LLC", "AL-1001", shipping17Address);
        Seller seller18 = createSeller("atyrau_energy", "info@atyrauenergy.kz", passwordEncoder.encode("sellerpass"), seller18Address, "Atyrau Energy Corp.", "KZ-060002", shipping18Address);
        Seller seller19 = createSeller("odessa_grains", "sales@odessagrains.ua", passwordEncoder.encode("sellerpass"), seller19Address, "Odessa Grain Exports", "UA-65000", shipping19Address);
        Seller seller20 = createSeller("riga_solutions", "contact@rigasolutions.lv", passwordEncoder.encode("sellerpass"), seller20Address, "Riga Solutions SIA", "LV-1050", shipping20Address);
        Seller seller21 = createSeller("cupertino_innovate", "info@cupertinoinnovate.com", passwordEncoder.encode("sellerpass"), seller21Address, "Cupertino Innovations Inc.", "US-95014", shipping21Address);
        Seller seller22 = createSeller("london_delights", "contact@londondelights.co.uk", passwordEncoder.encode("sellerpass"), seller22Address, "London Delights Ltd.", "UK-EC2R8EJ", shipping22Address);
        Seller seller23 = createSeller("ingolstadt_motors", "sales@ingolstadtmotors.de", passwordEncoder.encode("sellerpass"), seller23Address, "Ingolstadt Motors GmbH", "DE-85045", shipping23Address);
        Seller seller24 = createSeller("paris_chic", "contact@parischic.fr", passwordEncoder.encode("sellerpass"), seller24Address, "Paris Chic Fashion", "FR-75008", shipping24Address);
        Seller seller25 = createSeller("calgary_outdoor", "info@calgaryoutdoor.ca", passwordEncoder.encode("sellerpass"), seller25Address, "Calgary Outdoor Gear Inc.", "CA-T2P2V8", shipping25Address);
        Seller seller26 = createSeller("tokyo_electronics", "sales@tokyotech.co.jp", passwordEncoder.encode("sellerpass"), seller26Address, "Tokyo Electronics Co.", "JP-1410032", shipping26Address);
        Seller seller27 = createSeller("seoul_fashion", "contact@seoulfashion.kr", passwordEncoder.encode("sellerpass"), seller27Address, "Seoul Fashion House", "KR-06133", shipping27Address);
        Seller seller28 = createSeller("ny_traders", "info@nytraders.com", passwordEncoder.encode("sellerpass"), seller28Address, "New York Traders Inc.", "US-10118", shipping28Address);

        // Create Products

        Product product1 = createProduct("Smartphone X Pro", "High-end smartphone with advanced camera features and OLED display", 999.99, 15, Categories.ELECTRONICS, seller26 );
        Product product2 = createProduct("Ultra HD 4K TV", "65-inch Smart TV with vibrant colors, deep contrast, and smart connectivity", 1299.99, 10, Categories.ELECTRONICS, seller1);
        Product product3 = createProduct("Wireless Earbuds", "Compact, noise-cancelling earbuds offering superior sound quality", 199.99, 50, Categories.ELECTRONICS, seller23 );
        Product product4 = createProduct("Gaming Laptop", "High-performance laptop with RTX graphics and fast processor for gaming", 1499.99, 8, Categories.ELECTRONICS, seller12 );
        Product product5 = createProduct("Bluetooth Speaker", "Portable, waterproof speaker with deep bass and long battery life", 149.99, 30, Categories.ELECTRONICS, seller1 );
        Product product6 = createProduct("Designer Jeans", "Slim-fit designer denim with premium wash and stitching", 89.99, 40, Categories.CLOTHES, seller22);
        Product product7 = createProduct("Leather Jacket", "Genuine leather jacket with a modern cut and superior finish", 299.99, 25, Categories.CLOTHES, seller22);
        Product product8 = createProduct("Summer Floral Dress", "Lightweight and breezy dress perfect for summer outings", 59.99, 50, Categories.CLOTHES, seller6);
        Product product9 = createProduct("Running Shoes", "High-performance running shoes designed for comfort and durability", 99.99, 35, Categories.CLOTHES, seller15 );
        Product product10 = createProduct("Men's Formal Shirt", "Classic, crisp cotton shirt ideal for office and formal wear", 49.99, 60, Categories.CLOTHES, seller7 );
        Product product11 = createProduct("Historical Novel", "Engaging novel set in medieval Europe with rich historical detail", 19.99, 100, Categories.BOOKS, seller3 );
        Product product12 = createProduct("Science Fiction Anthology", "A collection of short sci-fi stories by renowned emerging authors", 24.99, 80, Categories.BOOKS, seller3);
        Product product13 = createProduct("Gourmet Cookbook", "A compendium of international recipes for the adventurous cook", 29.99, 75, Categories.BOOKS, seller11);
        Product product14 = createProduct("Children's Story Book", "Colorful illustrated story book to inspire young minds", 14.99, 120, Categories.BOOKS, seller4 );
        Product product15 = createProduct("Self-Help Guide", "Motivational guide with practical advice for personal growth", 17.99, 90, Categories.BOOKS, seller3);
        Product product16 = createProduct("Collectible Action Figure", "Highly-detailed action figure from a popular superhero franchise", 39.99, 60, Categories.TOYS, seller9 );
        Product product17 = createProduct("Educational Building Blocks", "Creative building blocks set designed to stimulate young imaginations", 49.99, 40, Categories.TOYS, seller10 );
        Product product18 = createProduct("Remote Control Car", "High-speed RC car featuring rechargeable battery and agile handling", 89.99, 30, Categories.TOYS, seller16 );
        Product product19 = createProduct("3D Puzzle Game", "Challenging 3D puzzle that tests critical thinking and spatial awareness", 24.99, 80, Categories.TOYS, seller7);
        Product product20 = createProduct("Mini Doll House", "Detailed doll house complete with furniture and accessories", 199.99, 15, Categories.TOYS, seller2 );
        Product product21 = createProduct("Modern Sofa", "Contemporary sofa upholstered in premium fabric with a sleek design", 799.99, 5, Categories.FURNITURE, seller20);
        Product product22 = createProduct("Elegant Dining Table Set", "Dining table with six matching chairs offering refined design", 999.99, 3, Categories.FURNITURE, seller20);
        Product product23 = createProduct("Ergonomic Office Desk", "Adjustable office desk with integrated storage for a streamlined workspace", 349.99, 10, Categories.FURNITURE, seller12);
        Product product24 = createProduct("Queen Bed Frame", "Sturdy, minimalist queen-size bed frame crafted for modern homes", 499.99, 7, Categories.FURNITURE, seller23);
        Product product25 = createProduct("Contemporary Bookshelf", "Sleek bookshelf designed with open shelving for display and storage", 149.99, 12, Categories.FURNITURE, seller20);
        Product product26 = createProduct("Organic Vegetable Mix", "A fresh assortment of locally sourced organic vegetables", 3.99, 200, Categories.GROCERIES, seller25);
        Product product27 = createProduct("Imported Gourmet Cheese", "Selection of aged cheeses imported from Europe", 12.99, 80, Categories.GROCERIES, seller25);
        Product product28 = createProduct("Premium Coffee Beans", "Richly roasted coffee beans for the perfect morning brew", 15.99, 100, Categories.GROCERIES, seller1);
        Product product29 = createProduct("Exotic Spice Collection", "Authentic spices curated from Indian and Middle Eastern markets", 8.99, 150, Categories.GROCERIES, seller26);
        Product product30 = createProduct("Artisanal Organic Honey", "Pure, unfiltered honey harvested from local beekeepers", 9.99, 90, Categories.GROCERIES, seller25);
        Product product31 = createProduct("Daily Multivitamin", "Essential multivitamin supplement to support overall wellness", 19.99, 70, Categories.HEALTH, seller6);
        Product product32 = createProduct("Herbal Detox Tea", "Soothing herbal tea blend to promote natural detoxification", 7.99, 100, Categories.HEALTH, seller6);
        Product product33 = createProduct("Advanced Fitness Tracker", "Wearable fitness tracker with heart rate monitoring and GPS", 129.99, 50, Categories.HEALTH, seller2);
        Product product34 = createProduct("Complete Skincare Set", "A full range of skincare products for healthy, radiant skin", 59.99, 40, Categories.BEAUTY, seller7);
        Product product35 = createProduct("Luxury Perfume", "Long-lasting fragrance combining floral and musk notes", 79.99, 35, Categories.BEAUTY, seller7);
        Product product36 = createProduct("Professional Makeup Kit", "Comprehensive makeup kit featuring high-pigment cosmetics", 49.99, 60, Categories.BEAUTY, seller24);
        Product product37 = createProduct("Water-Resistant Sports Watch", "Rugged sports watch with GPS, heart rate monitor, and durable build", 199.99, 25, Categories.SPORTS, seller16);
        Product product38 = createProduct("Eco-Friendly Yoga Mat", "Non-slip yoga mat with superior cushioning for comfortable practice", 29.99, 100, Categories.SPORTS, seller16);
        Product product39 = createProduct("Adjustable Dumbbell Set", "Versatile dumbbell set ideal for diverse home workouts", 89.99, 30, Categories.SPORTS, seller12);
        Product product40 = createProduct("Lightweight Tennis Racket", "Precision-engineered tennis racket suitable for all levels", 79.99, 20, Categories.SPORTS, seller2);
        Product product41 = createProduct("Board Game: Strategy Quest", "Engaging board game that challenges strategic thinking and problem solving", 39.99, 50, Categories.GAMES, seller3);
        Product product42 = createProduct("Racing Legends Video Game", "Immersive racing video game featuring realistic physics and graphics", 59.99, 40, Categories.GAMES, seller1);
        Product product43 = createProduct("Puzzle Game: Brain Teaser", "Fun and challenging puzzle game to stimulate critical thinking", 19.99, 80, Categories.GAMES, seller4);
        Product product44 = createProduct("Card Battle: Ultimate Showdown", "Fast-paced collectible card game with strategic battles", 29.99, 70, Categories.GAMES, seller5);
        Product product45 = createProduct("Retro Arcade Machine", "Miniature arcade machine preloaded with classic retro games", 249.99, 10, Categories.GAMES, seller1);
        Product product46 = createProduct("Portable E-Reader", "Lightweight e-reader with a high-resolution display and adjustable backlight", 119.99, 30, Categories.ELECTRONICS, seller2);
        Product product47 = createProduct("Feature-Packed Smartwatch", "Modern smartwatch with customizable faces and fitness tracking", 199.99, 25, Categories.ELECTRONICS, seller26);
        Product product48 = createProduct("Designer Handbag", "Luxury designer handbag crafted from premium materials", 499.99, 15, Categories.CLOTHES, seller22);
        Product product49 = createProduct("Electric Kettle", "Rapid-boil electric kettle with a stainless-steel finish and safety features", 39.99, 45, Categories.ELECTRONICS, seller23);
        Product product50 = createProduct("Noise Cancelling Headphones", "Over-ear headphones featuring active noise cancellation and superior sound", 299.99, 20, Categories.ELECTRONICS, seller26);
        Product product51 = createProduct("Smart Home Hub", "Centralized control for smart devices in your home", 129.99, 20, Categories.ELECTRONICS, seller3);
        Product product52 = createProduct("4K Action Camera", "Durable action camera with 4K recording and waterproof design", 249.99, 15, Categories.ELECTRONICS, seller26);
        Product product53 = createProduct("Noise Cancelling Earbuds", "Compact earbuds offering active noise cancellation and deep bass", 149.99, 35, Categories.ELECTRONICS, seller26);
        Product product54 = createProduct("Wireless Charger", "Fast wireless charging pad compatible with most smartphones", 39.99, 50, Categories.ELECTRONICS, seller1);
        Product product55 = createProduct("Gaming Keyboard", "Mechanical keyboard with RGB lighting and programmable keys", 89.99, 40, Categories.ELECTRONICS, seller2);
        Product product56 = createProduct("Ergonomic Mouse", "Wireless ergonomic mouse designed for comfort during long usage", 49.99, 60, Categories.ELECTRONICS, seller2);
        Product product57 = createProduct("Laptop Stand", "Adjustable stand to improve posture and increase laptop cooling", 29.99, 70, Categories.ELECTRONICS, seller12);
        Product product58 = createProduct("Smartwatch Pro", "Advanced smartwatch with health tracking and customizable faces", 199.99, 25, Categories.ELECTRONICS, seller26);
        Product product59 = createProduct("Fitness Band", "Lightweight fitness tracker with heart rate and sleep monitoring", 59.99, 80, Categories.HEALTH, seller16);
        Product product60 = createProduct("VR Headset", "Immersive virtual reality headset with high-resolution display", 299.99, 12, Categories.ELECTRONICS, seller1);
        Product product61 = createProduct("Digital SLR Camera", "Professional SLR camera with high resolution and interchangeable lenses", 899.99, 8, Categories.ELECTRONICS, seller26);
        Product product62 = createProduct("Portable Hard Drive", "2TB portable hard drive with fast data transfer speeds", 79.99, 45, Categories.ELECTRONICS, seller1);
        Product product63 = createProduct("External SSD 1TB", "Compact and fast 1TB external SSD with USB-C connectivity", 159.99, 30, Categories.ELECTRONICS, seller1);
        Product product64 = createProduct("UltraSlim Monitor", "27-inch monitor with ultra-slim bezel and vibrant display", 219.99, 18, Categories.ELECTRONICS, seller12);
        Product product65 = createProduct("Desktop Speaker Set", "High-quality desktop speakers with deep bass and clear treble", 99.99, 25, Categories.ELECTRONICS, seller23);
        Product product66 = createProduct("Electric Scooter", "Eco-friendly electric scooter with long battery life and smooth ride", 399.99, 10, Categories.ELECTRONICS, seller21);
        Product product67 = createProduct("Drone Quadcopter", "Compact drone with 4K camera and intuitive controls", 499.99, 7, Categories.ELECTRONICS, seller3);
        Product product68 = createProduct("Smart Light Bulbs", "Pack of energy-efficient, color-changing smart LED bulbs", 59.99, 60, Categories.ELECTRONICS, seller1);
        Product product69 = createProduct("Home Security Camera", "Wireless security camera with night vision and motion detection", 89.99, 35, Categories.ELECTRONICS, seller1);
        Product product70 = createProduct("Robot Vacuum Cleaner", "Automated vacuum cleaner with smart scheduling and obstacle detection", 299.99, 12, Categories.FURNITURE, seller20);
        Product product71 = createProduct("Leather Office Chair", "Ergonomic office chair with premium leather upholstery", 349.99, 8, Categories.FURNITURE, seller23);
        Product product72 = createProduct("Adjustable Standing Desk", "Height-adjustable desk designed for ergonomic office use", 429.99, 10, Categories.FURNITURE, seller12);
        Product product73 = createProduct("Ergonomic Mouse Pad", "Extra-large mouse pad with wrist support for enhanced comfort", 19.99, 75, Categories.ELECTRONICS, seller2);
        Product product74 = createProduct("Bluetooth Car Adapter", "Hands-free Bluetooth adapter for seamless in-car connectivity", 29.99, 50, Categories.ELECTRONICS, seller1);
        Product product75 = createProduct("Portable Projector", "Mini projector with HDMI input and built-in speaker", 259.99, 14, Categories.ELECTRONICS, seller2);
        Product product76 = createProduct("Smart Thermostat", "WiFi-enabled thermostat for efficient home climate control", 129.99, 20, Categories.ELECTRONICS, seller1);
        Product product77 = createProduct("UltraHD Monitor", "32-inch UltraHD monitor with high dynamic range and vivid colors", 349.99, 10, Categories.ELECTRONICS, seller12);
        Product product78 = createProduct("Mechanical Keyboard", "Custom mechanical keyboard with tactile feedback and RGB lighting", 99.99, 30, Categories.ELECTRONICS, seller2);
        Product product79 = createProduct("Gaming Headset", "Surround sound gaming headset with noise isolation and built-in mic", 79.99, 40, Categories.ELECTRONICS, seller1);
        Product product80 = createProduct("Portable Power Bank", "10,000mAh power bank with fast charging and compact design", 39.99, 55, Categories.ELECTRONICS, seller1);
        Product product81 = createProduct("Foldable Tablet", "Lightweight tablet with a foldable design for enhanced portability", 299.99, 15, Categories.ELECTRONICS, seller26);
        Product product82 = createProduct("Convertible 2-in-1 Laptop", "Versatile laptop convertible into a tablet for ultimate flexibility", 899.99, 9, Categories.ELECTRONICS, seller1);
        Product product83 = createProduct("Noise Reduction Microphone", "Studio-quality microphone with advanced noise reduction features", 129.99, 22, Categories.ELECTRONICS, seller1);
        Product product84 = createProduct("Graphic Drawing Tablet", "High-precision drawing tablet ideal for designers and digital artists", 249.99, 16, Categories.ELECTRONICS, seller12);
        Product product85 = createProduct("LED Desk Lamp", "Energy-saving LED desk lamp with adjustable brightness levels", 34.99, 40, Categories.FURNITURE, seller20);
        Product product86 = createProduct("Office Printer", "All-in-one wireless printer with scanning and copying features", 149.99, 12, Categories.ELECTRONICS, seller12);
        Product product87 = createProduct("Wireless Router", "High-speed wireless router with extensive coverage and security features", 89.99, 35, Categories.ELECTRONICS, seller1);
        Product product88 = createProduct("Fiber Optic Modem", "Reliable fiber optic modem with high data throughput", 79.99, 25, Categories.ELECTRONICS, seller1);
        Product product89 = createProduct("Surround Sound System", "Premium surround sound speaker system for immersive home theaters", 299.99, 10, Categories.ELECTRONICS, seller23);
        Product product90 = createProduct("Smart Door Lock", "Keyless smart door lock with remote access and security alerts", 159.99, 18, Categories.ELECTRONICS, seller1);
        Product product91 = createProduct("Digital Photo Frame", "High-resolution digital photo frame with slideshow and USB support", 69.99, 30, Categories.ELECTRONICS, seller1);
        Product product92 = createProduct("Fitness Smart Scale", "Smart scale that tracks weight, BMI, and body fat percentage", 49.99, 40, Categories.HEALTH, seller16);
        Product product93 = createProduct("Electric Toothbrush", "Rechargeable electric toothbrush with multiple cleaning modes", 39.99, 50, Categories.HEALTH, seller7);
        Product product94 = createProduct("Hair Dryer", "Powerful hair dryer with ionic technology for quick drying", 49.99, 35, Categories.HEALTH, seller7);
        Product product95 = createProduct("Luxury Cologne", "Sophisticated fragrance with lasting aroma for formal occasions", 89.99, 20, Categories.BEAUTY, seller7);
        Product product96 = createProduct("Scented Candle Set", "Set of premium scented candles to create a relaxing ambience", 29.99, 45, Categories.BEAUTY, seller24);
        Product product97 = createProduct("Designer Sunglasses", "High-fashion sunglasses with UV protection and modern design", 129.99, 20, Categories.CLOTHES, seller22);
        Product product98 = createProduct("Casual Sneakers", "Comfortable sneakers suitable for everyday wear and light sports", 69.99, 35, Categories.CLOTHES, seller22);
        Product product99 = createProduct("Running Shorts", "Lightweight and breathable running shorts for optimal performance", 29.99, 50, Categories.CLOTHES, seller6);
        Product product100 = createProduct("Sports Water Bottle", "Durable insulated water bottle perfect for workouts and outdoor activities", 19.99, 60, Categories.SPORTS, seller16);
        Product product101 = createProduct("Premium Arabian Oud Candle", "A beautifully scented candle infused with authentic Arabian oud, perfect for a luxurious ambience", 49.99, 40, Categories.BEAUTY, seller8);
        Product product102 = createProduct("Bluetooth Car Kit", "Seamless hands-free connectivity and audio streaming for your vehicle", 89.99, 25, Categories.ELECTRONICS, seller13);
        Product product103 = createProduct("Handcrafted Wooden Coffee Table", "Rustic coffee table made from locally sourced oak with a timeless design", 499.99, 5, Categories.FURNITURE, seller14);
        Product product104 = createProduct("Traditional Handmade Rug", "Authentic woven rug featuring intricate patterns inspired by Albanian heritage", 299.99, 8, Categories.FURNITURE, seller17);
        Product product105 = createProduct("Solar Panel Kit", "Complete solar panel kit ideal for small homes and outdoor setups", 399.99, 10, Categories.ELECTRONICS, seller18);
        Product product106 = createProduct("Organic Wheat Flour", "Premium organic wheat flour sourced directly from Ukrainian farms", 2.99, 100, Categories.GROCERIES, seller19);
        Product product107 = createProduct("Designer Streetwear Jacket", "Trendy streetwear jacket with urban design details and quality fabric", 149.99, 20, Categories.CLOTHES, seller27);
        Product product108 = createProduct("Vintage Record Player", "Classic record player with modern connectivity features and retro style", 249.99, 7, Categories.ELECTRONICS, seller28);
        Product product109 = createProduct("Modern Arabic Perfume", "Subtle yet captivating fragrance with notes of amber and musk", 79.99, 30, Categories.BEAUTY, seller8);
        Product product110 = createProduct("LED Car Interior Lights", "Customizable LED lighting kit to enhance your vehicle's interior ambience", 39.99, 50, Categories.ELECTRONICS, seller13);
        Product product111 = createProduct("Rustic Wooden Bookshelf", "Handcrafted bookshelf with a distressed finish, perfect for vintage decor", 199.99, 12, Categories.FURNITURE, seller14);
        Product product112 = createProduct("Embroidered Linen Scarf", "Elegant scarf featuring traditional Albanian embroidery on soft linen", 59.99, 35, Categories.CLOTHES, seller17);
        Product product113 = createProduct("High-Efficiency LED Floodlight", "Energy-saving floodlight with bright output suitable for outdoor areas", 129.99, 18, Categories.ELECTRONICS, seller18);
        Product product114 = createProduct("Imported Olive Oil", "Extra virgin olive oil imported from premium Mediterranean groves", 15.99, 60, Categories.GROCERIES, seller19);
        Product product115 = createProduct("Korean Style Sneakers", "Stylish sneakers combining modern design with classic Korean aesthetics", 89.99, 40, Categories.CLOTHES, seller27);
        Product product116 = createProduct("Smart Home Security System", "Integrated security system with smart sensors and remote monitoring", 399.99, 8, Categories.ELECTRONICS, seller28);
        Product product117 = createProduct("Designer Abaya", "Elegant abaya featuring modern cuts and luxurious detailing", 199.99, 15, Categories.CLOTHES, seller8);
        Product product118 = createProduct("Car Bluetooth Adapter", "Compact adapter enabling seamless Bluetooth connectivity in your car", 49.99, 45, Categories.ELECTRONICS, seller13);
        Product product119 = createProduct("Antique Oak Dining Table", "Exquisitely crafted dining table made from antique oak with intricate details", 899.99, 4, Categories.FURNITURE, seller14);
        Product product120 = createProduct("Handmade Ceramic Pottery", "Unique handcrafted pottery set, perfect for decor or dining", 69.99, 25, Categories.FURNITURE, seller17);
        Product product121 = createProduct("Portable Solar Charger", "Lightweight solar charger for smartphones and small devices", 59.99, 30, Categories.ELECTRONICS, seller18);
        Product product122 = createProduct("Organic Honey Jar", "Pure organic honey with a rich flavor, harvested from local beekeepers", 12.99, 50, Categories.GROCERIES, seller19);
        Product product123 = createProduct("Fashionable Leather Belt", "Durable leather belt featuring modern buckle designs and premium stitching", 39.99, 35, Categories.CLOTHES, seller27);
        Product product124 = createProduct("Retro Gaming Console", "Nostalgic retro console preloaded with classic games for modern players", 299.99, 10, Categories.GAMES, seller28);
        Product product125 = createProduct("Exquisite Arabic Calligraphy Art", "Limited edition calligraphy artwork blending tradition with modern elegance", 79.99, 15, Categories.BOOKS, seller8);
        Product product126 = createProduct("Advanced Car Diagnostic Tool", "Professional-grade diagnostic tool for comprehensive vehicle analysis", 149.99, 20, Categories.ELECTRONICS, seller13);
        Product product127 = createProduct("Custom Wood Carving Set", "Complete set for intricate wood carving, ideal for artisans and hobbyists", 89.99, 30, Categories.FURNITURE, seller14);
        Product product128 = createProduct("Traditional Albanian Music CD Collection", "A curated collection of traditional Albanian music from renowned artists", 19.99, 40, Categories.BOOKS, seller17);
        Product product129 = createProduct("Energy Efficient Inverter", "High-performance inverter designed for optimal energy conversion", 249.99, 12, Categories.ELECTRONICS, seller18);
        Product product130 = createProduct("Organic Sunflower Seeds", "Nutritious organic sunflower seeds perfect for snacks and baking", 3.99, 80, Categories.GROCERIES, seller19);


        // Create Offers


        createOffer(20.0, LocalDate.of(2025, 11, 25), LocalDate.of(2025, 11, 30), seller1, product2);  // US Black Friday - TV
        createOffer(15.0, LocalDate.of(2025, 12, 15), LocalDate.of(2025, 12, 25), seller22, product97); // UK Christmas - Sunglasses
        createOffer(25.0, LocalDate.of(2025, 4, 10), LocalDate.of(2025, 4, 15), seller8, product101);   // UAE Eid - Oud Candle
        createOffer(30.0, LocalDate.of(2025, 11, 25), LocalDate.of(2025, 11, 30), seller26, product47); // Japan Black Friday - Smartwatch
        createOffer(20.0, LocalDate.of(2025, 1, 21), LocalDate.of(2025, 2, 5), seller3, product13);    // China New Year - Cookbook
        createOffer(15.0, LocalDate.of(2025, 9, 20), LocalDate.of(2025, 10, 5), seller23, product126); // Germany Oktoberfest - Car Tools
        createOffer(25.0, LocalDate.of(2025, 7, 8), LocalDate.of(2025, 7, 15), seller17, product104);  // Albania Eid - Handmade Rug
        createOffer(30.0, LocalDate.of(2025, 2, 9), LocalDate.of(2025, 2, 14), seller24, product36);   // France Valentine's Day - Makeup Kit
        createOffer(20.0, LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 7), seller12, product23);    // Poland Labor Day - Office Desk
        createOffer(15.0, LocalDate.of(2025, 12, 26), LocalDate.of(2025, 1, 2), seller20, product21);  // Latvia Winter Sale - Sofa
        createOffer(25.0, LocalDate.of(2025, 6, 12), LocalDate.of(2025, 6, 18), seller6, product8);    // Spain Summer Sale - Floral Dress
        createOffer(30.0, LocalDate.of(2025, 9, 16), LocalDate.of(2025, 9, 19), seller13, product102); // Czech Auto Week - Car Kit
        createOffer(20.0, LocalDate.of(2025, 8, 15), LocalDate.of(2025, 8, 22), seller27, product115); // Korea Liberation Day - Sneakers
        createOffer(15.0, LocalDate.of(2025, 10, 3), LocalDate.of(2025, 10, 10), seller16, product18); // Serbia Tech Week - RC Car
        createOffer(25.0, LocalDate.of(2025, 4, 23), LocalDate.of(2025, 4, 29), seller21, product66);  // US Earth Day - Electric Scooter
        createOffer(30.0, LocalDate.of(2025, 7, 14), LocalDate.of(2025, 7, 21), seller24, product24);  // France Bastille Day - Bed Frame
        createOffer(20.0, LocalDate.of(2025, 2, 12), LocalDate.of(2025, 2, 19), seller4, product14);   // UAE Shopping Festival - Story Book
        createOffer(15.0, LocalDate.of(2025, 5, 5), LocalDate.of(2025, 5, 12), seller26, product52);   // Japan Golden Week - Action Camera
        createOffer(25.0, LocalDate.of(2025, 3, 17), LocalDate.of(2025, 3, 24), seller25, product30);  // Canada St. Patrick's - Organic Honey
        createOffer(30.0, LocalDate.of(2025, 11, 25), LocalDate.of(2025, 11, 30), seller2, product20); // NL Black Friday - Doll House
        createOffer(20.0, LocalDate.of(2025, 10, 31), LocalDate.of(2025, 11, 4), seller1, product79);  // US Halloween - Gaming Headset
        createOffer(15.0, LocalDate.of(2025, 8, 30), LocalDate.of(2025, 9, 6), seller19, product106);  // Ukraine Harvest Festival - Flour
        createOffer(25.0, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 7), seller15, product15);    // Croatia Children's Day - Self-Help Book
        createOffer(30.0, LocalDate.of(2025, 12, 1), LocalDate.of(2025, 12, 25), seller7, product35);  // Switzerland Advent - Luxury Perfume
        createOffer(20.0, LocalDate.of(2025, 9, 27), LocalDate.of(2025, 10, 4), seller5, product44);   // Thailand Tech Fair - Card Game
        createOffer(15.0, LocalDate.of(2025, 4, 22), LocalDate.of(2025, 4, 28), seller18, product105); // Kazakhstan Earth Day - Solar Panels
        createOffer(25.0, LocalDate.of(2025, 2, 10), LocalDate.of(2025, 2, 17), seller9, product16);   // Pakistan Kashmir Day - Action Figure
        createOffer(30.0, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 7), seller10, product17);    // NZ New Year - Building Blocks
        createOffer(20.0, LocalDate.of(2025, 3, 8), LocalDate.of(2025, 3, 15), seller14, product103);  // Romania Women's Day - Coffee Table
        createOffer(15.0, LocalDate.of(2025, 5, 9), LocalDate.of(2025, 5, 15), seller28, product116);  // US Memorial Day - Security System

        //creating orders

        // Link products to customer


        // Create Payment


        // Create Report

    }

    private Address createAddress(String line1, String line2, String city, String state, Country country, String zip) {
        Address address = new Address();
        address.setAddressLine1(line1);
        address.setAddressLine2(line2);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setZipCode(zip);
        return addressRepository.save(address);
    }

    private Customer createCustomer(String username, String email, String password, Address address) {
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setPersonalAddress(address);
        customer.setRole(Roles.CUSTOMER);
        customer.setBalance(10000.0);
        customer.setAmountSpent(0.0);
        customer.setAmountSaved(0.0);
        customer.setLoyaltyPoints(0);
        return customerRepository.save(customer);
    }


    private Seller createSeller(String username, String email, String password, Address personalAddress,
                                String businessName, String taxId, Address shippingAddress) {
        Seller seller = new Seller();
        seller.setUsername(username);
        seller.setEmail(email);
        seller.setPassword(password);
        seller.setPersonalAddress(personalAddress);
        seller.setRole(Roles.SELLER);
        seller.setBusinessName(businessName);
        seller.setTaxId(taxId);
        seller.setShippingAddress(shippingAddress);
        return sellerRepository.save(seller);
    }

    private Product createProduct(String name, String desc, double price, int quantity, Categories category, Seller owner) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(desc);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategory(category);  // Direct enum assignment
        product.setOwner(owner);
        owner.getOwnedProducts().add(product);
        return productRepository.save(product);
    }

    private Offer createOffer(double discount, LocalDate start, LocalDate end, Seller seller, Product product) {
        Offer offer = new Offer();
        offer.setDiscount(discount);
        offer.setStartDate(start);
        offer.setEndDate(end);
        offer.setSeller(seller);
        offer.setProduct(product);
        seller.getOffers().add(offer);
        return offerRepository.save(offer);
    }



    private Report createReport(Customer user, Product product, String desc, Report_Category category) {
        Report report = new Report();
        report.setUser(user);
        report.setProduct(product);
        report.setDescription(desc);
        report.setReportCategory(category);
        report.setTitle("Product Issue");
        return reportRepository.save(report);
    }

    private void clearData() {
        reportRepository.deleteAll();
        paymentRepository.deleteAll();
        offerRepository.deleteAll();
        productRepository.deleteAll();
        sellerRepository.deleteAll();
        customerRepository.deleteAll();
        addressRepository.deleteAll();
        userRepository.deleteAll();
    }
}

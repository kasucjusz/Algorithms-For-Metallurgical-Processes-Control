package agh.aspm.aspm.service;

import agh.aspm.aspm.model.MoldingDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.SECONDS;

@Component
public class CSVDataLoader {

    public List<MoldingDTO> getBySpeed(String speed) {
        List<MoldingDTO> moldingDTOS=fetchData().stream()
                .filter(x -> speed.equals(x.getMoldingSpeed()))
                .collect(Collectors.toList());
        moldingDTOS=reduceNotLongerThanTenMinutes(moldingDTOS);
        return moldingDTOS;
    }

    public List<MoldingDTO> getCSVFile() {
        return fetchData();
    }

    private List<MoldingDTO> fetchData() {
        List<MoldingDTO> moldingDTOS = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" );

        try(Scanner sc = new Scanner(new InputStreamReader(new ClassPathResource("aspm_data.csv").getInputStream()))) {
            sc.useDelimiter("\n");

            while (sc.hasNext())
            {
                String next = sc.next();
                String[] values = next.split(",");
                moldingDTOS.add(
                        MoldingDTO.builder()
                                .time(LocalDateTime.parse(values[0].substring(0,19), formatter))
                                .P1010(values[1])
                                .P1012(values[2])
                                .P1014(values[3])
                                .P1034(values[4])
                                .P1036(values[5])
                                .P1038(values[6])
                                .P1044(values[7])
                                .P1046(values[8])
                                .P1048(values[9])
                                .P1050(values[10])
                                .P1052(values[11])
                                .P1054(values[12])
                                .P1058(values[13])
                                .P1060(values[14])
                                .P1062(values[15])
                                .P1064(values[16])
                                .P1066(values[17])
                                .P1068(values[18])
                                .P1070(values[19])
                                .P1072(values[20])
                                .P1074(values[21])
                                .P1076(values[22])
                                .P1078(values[23])
                                .P1080(values[24])
                                .P1094(values[25])
                                .P1096(values[26])
                                .P1098(values[27])
                                .P1100(values[28])
                                .P1102(values[29])
                                .P1104(values[30])
                                .P1106(values[31])
                                .P1108(values[32])
                                .P1110(values[33])
                                .P1112(values[34])
                                .P1114(values[35])
                                .P1118(values[36])
                                .P1120(values[37])
                                .P1122(values[38])
                                .P1124(values[39])
                                .P1126(values[40])
                                .P1128(values[41])
                                .P1130(values[42])
                                .P1132(values[43])
                                .P1134(values[44])
                                .P1136(values[45])
                                .P1138(values[46])
                                .P1140(values[47])
                                .P1142(values[48])
                                .P1144(values[49])
                                .P1146(values[50])
                                .P1148(values[51])
                                .P1150(values[52])
                                .P1152(values[53])
                                .P1154(values[54])
                                .P1156(values[55])
                                .P1158(values[56])
                                .P1160(values[57])
                                .P1162(values[58])
                                .P1164(values[59])
                                .P1166(values[60])
                                .P1168(values[61])
                                .P1170(values[62])
                                .P1172(values[63])
                                .P1174(values[64])
                                .P1176(values[65])
                                .P1178(values[66])
                                .P1180(values[67])
                                .P1182(values[68])
                                .P1184(values[69])
                                .P1186(values[70])
                                .P1188(values[71])
                                .P1192(values[72])
                                .P1194(values[73])
                                .P1426(values[74])
                                .P1427(values[75])
                                .P1433(values[76])
                                .P1434(values[77])
                                .P1435(values[78])
                                .P1440(values[79])
                                .moldingSpeed(calculateMoldingSpeed(values[4]))
                                .build()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moldingDTOS;
    }

    private String calculateMoldingSpeed(String val) {
        return new BigDecimal(val)
                .setScale(1, RoundingMode.HALF_UP)
                .toPlainString();
    }
    private List<MoldingDTO> reduceNotLongerThanTenMinutes(List<MoldingDTO> moldingDTOS){
        //TODO
        //Jeśli różnica dwóch sąsiednich <=10s - dodajemy element do nowej listy i zapisujemy w jednej zmiennej tymczasowej jego indeks
        //W momencie napotkania różnicy większej niż 10s porównujemy czy czas na startowym indeksie z obecnym jest <=10min
        List<MoldingDTO> reducedList=new ArrayList<>();
        List<MoldingDTO> tmpList=new ArrayList<>();
        int beginningOfTheCycle=0;
        Duration durationTenSeconds=Duration.ofSeconds(30);
        Duration durationTenMinutes=Duration.ofMinutes(10);//podmiana na wartość jako argument metody?
        for(int i=0; i<moldingDTOS.size(); i++){
            System.out.println("XXXXXXXXXXXXXXXXXXXPUNKT STARTOWY CYKLUXXXXXXXXXXXX  "+ beginningOfTheCycle);
        if(i<moldingDTOS.size()-1){
            Duration determineIfLastedTenSeconds = Duration.between(moldingDTOS.get(i).getTime(), moldingDTOS.get(i+1).getTime());
            if(determineIfLastedTenSeconds.compareTo(durationTenSeconds)<=0){
                System.out.println("Roznica mniejsza niz 10s");
                System.out.println(moldingDTOS.get(i).getTime());
                tmpList.add(moldingDTOS.get(i));
            }
            else {
                System.out.println("Roznica wieksza niz 10s");
                System.out.println(moldingDTOS.get(i).getTime());
                Duration determineIfLastedTenMinutes= Duration.between(moldingDTOS.get(beginningOfTheCycle).getTime(), moldingDTOS.get(i).getTime());
                if(determineIfLastedTenMinutes.compareTo(durationTenMinutes)<=0){
                    System.out.println("-------------ROZNICA W CYKLACH MNIEJSZA NIZ 10 MIN");
                    tmpList.clear();
                    beginningOfTheCycle=i+1;
                }
                else {
                    System.out.println("++++++++++ROZNICA W CYKLACH WIEKSZA NIZ 10 MIN+++++++++");
                    reducedList.addAll(tmpList);
                    tmpList.clear();
                    beginningOfTheCycle=i+1;

                }


            }
        }
            }
return reducedList;
    }
}


package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.PrijimaciOborList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;




public class Main7 {
    public static void main(String[] args) {
        specializationDeadlines(2025);
    }
    public static String specializationDeadlines(int year) {
        String json = Api.getSpecializations(year);
        PrijimaciOborList prijimaciObor = new Gson().fromJson(json, PrijimaciOborList.class);
        HashMap<String,String> hashMap= new HashMap<>();
        prijimaciObor.prijimaciObor.stream().forEach(a->{
            hashMap.put(a.eprDeadlinePrihlaska.value, a.eprDeadlinePrihlaska.value);
        });
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
       return hashMap.entrySet().stream().map(entry -> entry.getKey()).map(dateStr -> LocalDate.parse(dateStr, formatter)).sorted().map(date -> date.format(formatter)).collect(Collectors.joining(","));

    }
}

package mx.ipn.closure.services;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClosureService {

    public List<String> generateBinaryStrings(int n) {
        List<String> result = new ArrayList<>();
        int maxNumber = (1 << n); // 2^n

        for (int i = 0; i < maxNumber; i++) {
            String binaryString = String.format("%" + n + "s", Integer.toBinaryString(i)).replace(' ', '0');
            result.add(binaryString);
        }

        return result;
    }

    public Map<String, String> generatekleeneStar(int number){
        Hashtable<String, String> kleeneStar = new Hashtable<>();
        List<String> binaryString = generateBinaryStrings(number);
        binaryString.addFirst("λ"); //void string
        kleeneStar.put("Σ^*", binaryString.toString());
        return kleeneStar;
    }

    public Map<String, String> generatekleenePlus(int number){
        Hashtable<String, String> kleenePlus = new Hashtable<>();
        List<String> binaryString = generateBinaryStrings(number);
        kleenePlus.put("Σ^+", binaryString.toString());
        return kleenePlus;
    }


}

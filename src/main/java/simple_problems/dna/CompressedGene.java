package simple_problems.dna;

import java.util.BitSet;

public class CompressedGene {
    private BitSetExtended bitSet;
    private int length;

    public BitSet getBitSet() {
        return bitSet;
    }

    public void compress(String gene) {
        length = gene.length();
        bitSet = new BitSetExtended(length * 2);
        final String upperGene = gene.toUpperCase();
        for (int i = 0; i < length; i++) {
            int firstLocation = 2 * i;
            int secondLocation = 2 * i + 1;
            switch (upperGene.charAt(i)) {
                case 'A' -> {
                    bitSet.set(firstLocation, false);
                    bitSet.set(secondLocation, false);
                }
                case 'C' -> {
                    bitSet.set(firstLocation, false);
                    bitSet.set(secondLocation, true);
                }
                case 'D' -> {
                    bitSet.set(firstLocation, true);
                    bitSet.set(secondLocation, false);
                }
                case 'T' -> {
                    bitSet.set(firstLocation, true);
                    bitSet.set(secondLocation, true);
                }
                default -> throw new IllegalArgumentException("Gene string contains illegal character: "+upperGene.charAt(i));
            }
        }
    }

    public String decompress(){
        if(bitSet==null)return "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < (length*2); i+=2) {
            int firstBit = (bitSet.get(i)?1:0);
            int secondBit = (bitSet.get(i+1)?1:0);
            int lastBit = firstBit<<1|secondBit;
            switch (lastBit){
                case 0b00 -> builder.append('A');
                case 0b01 -> builder.append('C');
                case 0b10 -> builder.append('D');
                case 0b11 -> builder.append('T');
            }

        }
        return builder.toString();
    }
}

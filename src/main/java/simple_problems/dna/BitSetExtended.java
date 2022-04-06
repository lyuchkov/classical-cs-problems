package simple_problems.dna;

import java.util.BitSet;

public class BitSetExtended extends BitSet {
    int trueCount =0;
    int falseCount=0;

    public BitSetExtended(int i) {
        super(i);
    }


    @Override
    public void set(int bitIndex, boolean value) {
        super.set(bitIndex, value);
        if (value) {
            trueCount++;
        } else {
            falseCount++;
        }
    }
}

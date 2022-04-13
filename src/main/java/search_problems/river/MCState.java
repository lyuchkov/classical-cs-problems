package search_problems.river;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class MCState {
    private static final int MAX_NUM = 3;
    public final int wm;
    public final int wc;
    public final int em;
    public final int ec;
    public final boolean boat;

    public MCState(int missionaries, int cannibals, boolean boat) {
        wm = missionaries;
        wc = cannibals;
        em = MAX_NUM - wm;
        ec = MAX_NUM - wc;
        this.boat = boat;
    }

    public static List<MCState> successors(MCState mcs) {
        List<MCState> sucs = new ArrayList<>();
        if (mcs.boat) {
            if (mcs.wm > 1) {
                sucs.add(new MCState(mcs.wm - 2, mcs.wc, false));
            }
            if (mcs.wm > 0) {
                sucs.add(new MCState(mcs.wm - 1, mcs.wc, false));
            }
            if (mcs.wc > 1) {
                sucs.add(new MCState(mcs.wm, mcs.wc - 2, false));
            }
            if (mcs.wc > 0) {
                sucs.add(new MCState(mcs.wm, mcs.wc - 1, false));
            }
            if (mcs.wc > 0 && mcs.wm > 0) {
                sucs.add(new MCState(mcs.wm - 1, mcs.wc - 1, false));
            }
        } else {
            if (mcs.em > 1) {
                sucs.add(new MCState(mcs.wm + 2, mcs.wc, true));
            }
            if (mcs.em > 0) {
                sucs.add(new MCState(mcs.wm + 1, mcs.wc, true));
            }
            if (mcs.ec > 1) {
                sucs.add(new MCState(mcs.wm, mcs.wc + 2, true));
            }
            if (mcs.ec > 0) {
                sucs.add(new MCState(mcs.wm, mcs.wc + 1, true));
            }
            if (mcs.ec > 0 && mcs.em > 0) {
                sucs.add(new MCState(mcs.wm + 1, mcs.wc + 1, true));
            }
        }
        sucs.removeIf(Predicate.not(MCState::isLegal));
        return sucs;
    }

    @Override
    public String toString() {
        return String.format("On the west bank there are %d missionaries and %d cannibals.%n"
                +"On the east bank there are %d missionaries and and %d cannibals.%n"
                +"The boat is on the %s bank.",
                wm, wc, em, ec,
                boat ? "west" : "east");
    }

    public boolean isGoal() {
        return isLegal() && em == MAX_NUM && ec == MAX_NUM;
    }

    private boolean isLegal() {
        return (wm >= wc || wm <= 0) && (em >= ec || em <= 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MCState mcState = (MCState) o;
        return wm == mcState.wm && wc == mcState.wc && em == mcState.em && ec == mcState.ec && boat == mcState.boat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wm, wc, em, ec, boat);
    }
}

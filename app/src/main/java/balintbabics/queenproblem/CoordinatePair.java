package balintbabics.queenproblem;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
/**
 * Created by bbabics on 2017. 03. 20..
 */

@AutoValue
abstract class CoordinatePair implements Parcelable {

    public static CoordinatePair create(int x, int y) {
        return new AutoValue_CoordinatePair(x, y);
    }

    public abstract int x();

    public abstract int y();

    @Override
    public String toString() {
        return (String.valueOf('A' + x())) + String.valueOf(y());
    }
}

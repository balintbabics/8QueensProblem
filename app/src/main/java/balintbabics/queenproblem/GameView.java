package balintbabics.queenproblem;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;

/**
 * Created by BalintBabics on 2017. 03. 18..
 */

public class GameView extends View {

    private static final int MARGIN_DP = 32;

    static int MARGIN;

    private static String TAG = "GameView"; // or GameView.class.getSimpleName();

    @BindView(R.id.game_view)
    public LinearLayout gameView;

    int QUEENSCOUNT = 8;
    int rows;
    int columns;
    boolean drawQueen[][];

    Paint paint;
    Drawable queenIcon;

    public GameView(Context context) {
        super(context);
        init(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(21)
    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        if(!isInEditMode()) {

            rows = columns = QUEENSCOUNT;
            MARGIN = dpToPx(MARGIN_DP);

            drawQueen = new boolean[rows][columns];
            for(int i = 0; i < QUEENSCOUNT; i++) {
                for(int j = 0; j < columns; j++) {
                    drawQueen[i][j] = false;
                }
            }

            queenIcon = ContextCompat.getDrawable(context, R.drawable.queen);
            paint = new Paint();
        }
    }

    public static int dpToPx(double dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    private int getFloatAsInt(float input) {
        return ((Float) input).intValue();
    }

    private void drawChessTable(Canvas canvas) {
        int size = getWidth();
        float halfSize = ((float) size) / 2;

        float halfGrid = halfSize - MARGIN;
        float cellSize = halfGrid / (((float) QUEENSCOUNT) / 2);

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                paint.setStyle(Paint.Style.FILL);

                if((i + j) % 2 == 0) {
                    paint.setColor(Color.WHITE);
                } else {
                    paint.setColor(Color.BLACK);
                }
                canvas.drawRect(MARGIN + j * cellSize, MARGIN + i * cellSize, MARGIN + (j + 1) * cellSize, MARGIN + (i + 1) * cellSize, paint);

                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(5);
                canvas.drawRect(MARGIN + j * cellSize, MARGIN + i * cellSize, MARGIN + (j + 1) * cellSize, MARGIN + (i + 1) * cellSize, paint);

                if(drawQueen[i][j]) {
                    queenIcon.setBounds(getFloatAsInt(MARGIN + j * cellSize), getFloatAsInt(MARGIN + i * cellSize), getFloatAsInt(MARGIN + (j + 1) * cellSize), getFloatAsInt(MARGIN + (i + 1) * cellSize));
                    queenIcon.draw(canvas);
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawChessTable(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int min = Math.min(getMeasuredHeight(), getMeasuredWidth());
        setMeasuredDimension(min, min);
    }
}

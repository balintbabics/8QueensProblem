package balintbabics.queenproblem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;

/**
 * Created by BalintBabics on 2017. 03. 18..
 */

public class GameView extends View {

    private static String TAG = "GameView"; // or GameView.class.getSimpleName();

    @BindView(R.id.game_view)
    public LinearLayout gameView;

    int rows;
    int columns;
    int QUEENSCOUNT = 8;
    int cellSize = 100;

    static int startX;
    static int startY;

    boolean drawQueen[][];

    Context context;
    Paint paint;
    Drawable queenIcon;

    public GameView(Context context) {
        super(context);

        this.context = context;

        startX = 135;
        startY = 100;

        rows = QUEENSCOUNT;
        columns = QUEENSCOUNT;

        drawQueen = new boolean[rows][columns];

        for(int i = 0 ; i < rows ; i++) {
            for( int j = 0 ; j < columns ; j++) {
                drawQueen[i][j] = false;
            }
        }

        queenIcon = ContextCompat.getDrawable(context, R.drawable.queen);

        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawChessTable(canvas);
    }

    private void drawChessTable(Canvas canvas) {

        int X = startX;
        int Y = startY;

        int k = 0;

        for(int i = 0 ; i < rows ; i++) {
            for( int j = 0 ; j < columns ; j++) {

                paint.setStyle(Paint.Style.FILL);

                if(k % 2 == 0) {
                    paint.setColor(Color.WHITE);
                    canvas.drawRect( X, Y, X + cellSize, Y + cellSize, paint);
                }
                else {
                    paint.setColor(Color.BLACK);
                    canvas.drawRect( X, Y, X + cellSize, Y + cellSize, paint);
                }

                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(5);
                canvas.drawRect( X, Y, X + cellSize, Y + cellSize, paint);
                if(drawQueen[i][j]) {
                    queenIcon.setBounds(X, Y, X+cellSize, Y+cellSize);
                    queenIcon.draw(canvas);
                }
                k++;
                X += cellSize;
            }

            X = startX;
            Y += cellSize;
            if(rows % 2 == 0) {
                k++;
            }
        }
    }

    protected void drawQueenToGivenPosition(int posX, int posY) {
        drawQueen[posX][posY] = true;
    }

}

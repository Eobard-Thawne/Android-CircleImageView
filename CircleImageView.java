package com.example.my_first_match_app.my_loop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class CircleImageView extends AppCompatImageView {
    /* 默认圆角大小为30 */
    private int angle = 30;
    /* 是否设置四个角为圆角 */
    private boolean all;
    /* 左上/左下/右上/右下角的圆角大小 */
    private int leftTopAngle, leftBottomAngle, rightTopAngle, rightBottomAngle;
    /* 是否左上/左下/右上/右下角为圆角 */
    private boolean leftTop, leftBottom, rightTop, rightBottom;
    /* 控件的宽和高 */
    private float width, height;
    /* 用于画矩形或者对矩形进行裁剪 */
    private final Path path = new Path();
    /* 矩形的样子, 多大, 坐标存放在此对象中 */
    private final RectF rf = new RectF();
    /* 创建一个8个字节长度的数组, 其中每两个数据代表正方形的一个点,[0],[1]代表左上角,以此类推 */
    private final float[] rids = new float[8];
    /* 画圆角的方向  CW为顺时针(左上/右上/右下/左下) CCW为逆时针(左下开始) */
    private final Path.Direction direction = Path.Direction.CW;

    public CircleImageView(@NonNull Context context) {
        super(context);
    }

    public CircleImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();

    }

    /**
     * 设置圆角大小, 此方法就默认代表四个角都设置为圆角
     *
     * @param angle 圆角大小
     * @return CircleImageView
     */

    public CircleImageView setAngle(int angle) {
        this.angle = angle;
        this.all = true;
        return this;
    }

    /**
     * 设置左上角的圆角
     *
     * @param leftTopAngle 左上角的圆角大小
     * @return CircleImageView
     */

    public CircleImageView setLeftTopAngle(int leftTopAngle) {
        this.leftTopAngle = leftTopAngle;
        leftTop = true;
        return this;
    }

    /**
     * 设置右上角的圆角
     *
     * @param rightTopAngle 右上角的圆角大小
     * @return CircleImageView
     */

    public CircleImageView setRightTopAngle(int rightTopAngle) {
        this.rightTopAngle = rightTopAngle;
        rightTop = true;
        return this;
    }

    /**
     * 设置右下角的圆角
     *
     * @param rightBottomAngle 右下角的圆角大小
     * @return CircleImageView
     */

    public CircleImageView setRightBottomAngle(int rightBottomAngle) {
        this.rightBottomAngle = rightBottomAngle;
        rightBottom = true;
        return this;
    }

    /**
     * 设置左下角的圆角
     *
     * @param leftBottomAngle 左下角的圆角大小
     * @return CircleImageView
     */

    public CircleImageView setLeftBottomAngle(int leftBottomAngle) {
        this.leftBottomAngle = leftBottomAngle;
        leftBottom = true;
        return this;
    }

    /**
     * 同时设置左上角和右上角的圆角
     *
     * @param leftTopAngle  左上角的圆角大小
     * @param rightTopAngle 右上角的圆角大小
     * @return CircleImageView
     */

    public CircleImageView setLeftTopRightTop(int leftTopAngle, int rightTopAngle) {
        this.leftTopAngle = leftTopAngle;
        this.rightTopAngle = rightTopAngle;
        leftTop = true;
        rightTop = true;
        return this;
    }

    /**
     * 同时设置左上角和右下角的圆角
     *
     * @param leftTopAngle     左上角的圆角大小
     * @param rightBottomAngle 右下角的圆角大小
     * @return CircleImageView
     */

    public CircleImageView setLeftTopRightBottom(int leftTopAngle, int rightBottomAngle) {
        this.leftTopAngle = leftTopAngle;
        this.rightBottomAngle = rightBottomAngle;
        leftTop = true;
        rightBottom = true;
        return this;
    }

    /**
     * 同时设置左上角和左下角的圆角
     *
     * @param leftTopAngle    左上角的圆角大小
     * @param leftBottomAngle 右上角的圆角大小
     * @return CircleImageView
     */

    public CircleImageView setLeftTopLeftBottom(int leftTopAngle, int leftBottomAngle) {
        this.leftTopAngle = leftTopAngle;
        this.leftBottomAngle = leftBottomAngle;
        leftTop = true;
        leftBottom = true;
        return this;
    }

    /**
     * 同时设置左下角和右上角的圆角
     *
     * @param leftBottomAngle 左下角的圆角
     * @param rightTopAngle   右上角的圆角
     * @return CircleImageView
     */

    public CircleImageView setLeftBottomRightTop(int leftBottomAngle, int rightTopAngle) {
        this.leftBottomAngle = leftBottomAngle;
        this.rightTopAngle = rightTopAngle;
        leftBottom = true;
        rightTop = true;
        return this;

    }

    /**
     * 同时设置左下角和右下角的圆角
     *
     * @param leftBottomAngle  左下角的圆角
     * @param rightBottomAngle 右下角的圆角
     * @return CircleImageView
     */

    public CircleImageView setLeftBottomRightBottom(int leftBottomAngle, int rightBottomAngle) {
        this.leftBottomAngle = leftBottomAngle;
        this.rightBottomAngle = rightBottomAngle;
        leftBottom = true;
        rightBottom = true;
        return this;
    }

    /**
     * 同时设置右下角和右上角的圆角
     *
     * @param rightBottomAngle 右下角的圆角大小
     * @param rightTopAngle    右上角的圆角大小
     * @return CircleImageView
     */

    public CircleImageView setRightBottomRightTop(int rightBottomAngle, int rightTopAngle) {
        this.rightBottomAngle = rightBottomAngle;
        this.rightTopAngle = rightTopAngle;
        rightBottom = true;
        rightTop = true;
        return this;
    }

    private void applyLeftTop(Canvas canvas) {
        rids[0] = this.leftTopAngle;
        rids[1] = this.leftTopAngle;
        rf.set(0, 0, width, height);
        path.addRoundRect(rf, rids, direction);
        canvas.clipPath(path);

    }

    private void applyRightTop(Canvas canvas) {
        rids[2] = this.rightTopAngle;
        rids[3] = this.rightTopAngle;
        rf.set(0, 0, width, height);
        path.addRoundRect(rf, rids, direction);
        canvas.clipPath(path);
    }

    private void applyRightBottom(Canvas canvas) {
        rids[4] = this.rightBottomAngle;
        rids[5] = this.rightBottomAngle;
        rf.set(0, 0, width, height);
        path.addRoundRect(rf, rids, direction);
        canvas.clipPath(path);
    }

    private void applyLeftBottom(Canvas canvas) {
        rids[6] = this.leftBottomAngle;
        rids[7] = this.leftBottomAngle;
        rf.set(0, 0, width, height);
        path.addRoundRect(rf, rids, direction);
        canvas.clipPath(path);
    }

    private void applyAll(Canvas canvas) {
        for (float i = 0; i < rids.length; i++) {
            rids[(int) i] = angle;
        }
        rf.set(0, 0, width, height);
        path.addRoundRect(rf, rids, direction);
        canvas.clipPath(path);
    }

    private void applyLeftTopRightTop(Canvas canvas) {
        rids[0] = leftTopAngle;
        rids[1] = leftTopAngle;
        rids[2] = rightTopAngle;
        rids[3] = rightTopAngle;
        rf.set(0, 0, width, height);
        path.addRoundRect(rf, rids, direction);
        canvas.clipPath(path);
    }

    private void applyLeftTopRightBottom(Canvas canvas) {
        rids[0] = leftTopAngle;
        rids[1] = leftTopAngle;
        rids[4] = rightBottomAngle;
        rids[5] = rightBottomAngle;
        rf.set(0, 0, width, height);
        path.addRoundRect(rf, rids, direction);
        canvas.clipPath(path);
    }

    private void applyLeftTopLeftBottom(Canvas canvas) {
        rids[0] = leftTopAngle;
        rids[1] = leftTopAngle;
        rids[6] = leftBottomAngle;
        rids[7] = leftBottomAngle;
        rf.set(0, 0, width, height);
        path.addRoundRect(rf, rids, direction);
        canvas.clipPath(path);
    }

    private void applyRightTopRightBottom(Canvas canvas) {
        rids[2] = rightTopAngle;
        rids[3] = rightTopAngle;
        rids[4] = rightBottomAngle;
        rids[5] = rightBottomAngle;
        rf.set(0, 0, width, height);
        path.addRoundRect(rf, rids, direction);
        canvas.clipPath(path);
    }

    private void applyRightTopLeftBottom(Canvas canvas) {
        rids[2] = rightTopAngle;
        rids[3] = rightTopAngle;
        rids[6] = leftBottomAngle;
        rids[7] = leftBottomAngle;
        rf.set(0, 0, width, height);
        path.addRoundRect(rf, rids, direction);
        canvas.clipPath(path);
    }

    private void applyRightBottomLeftBottom(Canvas canvas) {
        rids[4] = rightBottomAngle;
        rids[5] = rightBottomAngle;
        rids[6] = leftBottomAngle;
        rids[7] = leftBottomAngle;
        rf.set(0, 0, width, height);
        path.addRoundRect(rf, rids, direction);
        canvas.clipPath(path);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (leftTop && rightTop && leftBottom && rightBottom) {
            applyAll(canvas);
        }
        if (leftTop && rightTop) {
            applyLeftTopRightTop(canvas);
        } else if (leftTop && rightBottom) {
            applyLeftTopRightBottom(canvas);
        } else if (leftTop && leftBottom) {
            applyLeftTopLeftBottom(canvas);
        } else if (rightTop && rightBottom) {
            applyRightTopRightBottom(canvas);
        } else if (rightTop && leftBottom) {
            applyRightTopLeftBottom(canvas);
        } else if (rightBottom && leftBottom) {
            applyRightBottomLeftBottom(canvas);
        }
        if (all) {
            applyAll(canvas);
        } else if (leftTop) {
            applyLeftTop(canvas);
        } else if (rightTop) {
            applyRightTop(canvas);
        } else if (rightBottom) {
            applyRightBottom(canvas);
        } else if (leftBottom) {
            applyLeftBottom(canvas);
        }


//            path.moveTo(angle, 0); // (30, 0)
//            path.lineTo(width - angle, 0); // (1050, 0)
//            path.quadTo(width, 0, width, angle); // (1050, 0, 1050, 30)
//            path.lineTo(width, height - angle); // (1050, 630)
//            path.quadTo(width, height, width - angle, height); // (1050, 630, 1020, 630)
//            path.lineTo(angle, height); // (30, 630)
//            path.quadTo(0, height, 0, height - angle); // (0, 1050, 0 1020)
//            path.lineTo(0, angle); // (0, 30)
//            path.quadTo(0, 0, 40, 0); // (0, 0, 40, 0)
//            canvas.clipPath(path); // 截取这四组坐标, 这四组坐标是一个闭环, 相当手机中给照片截取圆角图片

        super.onDraw(canvas);
    }
}

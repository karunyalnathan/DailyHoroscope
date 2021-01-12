package com.kln.dailyhoroscope

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout

class DHRow @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var row: View = LayoutInflater.from(context).inflate(R.layout.dh_row,this)
    private var dhLabel: AppCompatTextView = row.findViewById(R.id.horoscope_label)
    private var dhValue: AppCompatTextView = row.findViewById(R.id.horoscope_value)

    init {
        //getting custom attributes
        val styleAttr: TypedArray = context.resources.obtainAttributes(attrs, R.styleable.DHRow)

        val label = styleAttr.getString(R.styleable.DHRow_labelText)
        val value = styleAttr.getString(R.styleable.DHRow_valueText)
        val isTable = styleAttr.getBoolean(R.styleable.DHRow_useTableStyle, false)
        val isTopRow = styleAttr.getBoolean(R.styleable.DHRow_isTopRow,true)

        //recycling custom attributes
        styleAttr.recycle()

        //applying attributes
        dhLabel.text = label;
        dhValue.text = value;

        val stack: Int = context.resources.getDimensionPixelSize(R.dimen.stacked_narrow_space);
        val inline: Int = context.resources.getDimensionPixelSize(R.dimen.inline_narrow_space);

        if(isTable && isTopRow){
            dhLabel.apply {
                setPadding(inline, stack, inline, stack)
                setBackgroundResource(R.drawable.table_top_firstcolumn)
            }
            dhValue.apply {
                dhValue.setPadding(inline, stack, inline, stack)
                dhValue.setBackgroundResource(R.drawable.table_top_ncolumn)
            }
            findViewById<View>(R.id.divider_vertical).visibility = View.VISIBLE

        }else if(isTable && !isTopRow){
            dhLabel.apply {
                setPadding(inline, stack, inline, stack)
                setBackgroundResource(R.drawable.table_bottom_firstcolumn)
            }
            dhValue.apply {
                dhValue.setPadding(inline, stack, inline, stack)
                dhValue.setBackgroundResource(R.drawable.table_bottom_ncolumn)
            }
            findViewById<View>(R.id.divider_vertical).visibility = View.VISIBLE

        }else{
            setPadding(inline, stack, inline, stack)
        }

    }
    


    public fun setLabel(@StringRes label: Int){
        dhLabel.setText(label)
    }


    public fun setValue(value: String){
        dhValue.text = value
    }



}
package com.kln.dailyhoroscope

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class ZodiacRecyclerViewAdapter(signSelectedInterface : ZodiacRecyclerViewHolder.OnSignSelectedClickListener.SignSelectedInterface) : RecyclerView.Adapter<ZodiacRecyclerViewAdapter.ZodiacRecyclerViewHolder?>() {


    private val mOnSignSelectedInterface : ZodiacRecyclerViewHolder.OnSignSelectedClickListener.SignSelectedInterface = signSelectedInterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZodiacRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(viewType, parent,false)
        return ZodiacRecyclerViewHolder(view)

    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.zodiac_viewholder
    }

    override fun getItemCount(): Int {
        return Zodiac.values().size
    }

    override fun onBindViewHolder(holder: ZodiacRecyclerViewHolder, position: Int) {
        holder.onBind(position, mOnSignSelectedInterface)
    }

    class ZodiacRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val signName: AppCompatTextView = itemView.findViewById<AppCompatTextView>(R.id.zodiacVHtextView)
        private val signSymbol: ImageView = itemView.findViewById<ImageView>(R.id.zodiacVHimageView)
        private val zSign : View = itemView


        fun onBind(zodiacSign: Int, onSelected : OnSignSelectedClickListener.SignSelectedInterface){
            signName.setText(Zodiac.values()[zodiacSign].signName)
            signSymbol.setImageResource(Zodiac.values()[zodiacSign].signGlyph)

            val onClickListener = OnSignSelectedClickListener(Zodiac.values()[zodiacSign], onSelected)
            zSign.setOnClickListener(onClickListener)

        }

        class OnSignSelectedClickListener(sign : Zodiac, signSelectedInterface: SignSelectedInterface) : View.OnClickListener{
            private var mSign : Zodiac = sign
            private val mSignSelectedInterface: SignSelectedInterface = signSelectedInterface

            interface SignSelectedInterface{
                fun onSignSelected(sign: Zodiac)
            }
            override fun onClick(v: View?) {
                mSignSelectedInterface.onSignSelected(mSign)
            }


        }

    }




}
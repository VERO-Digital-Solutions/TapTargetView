package com.getkeepsafe.taptargetviewsample

import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        list.adapter = Adapter(this)
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun showTapTargetOnLastItem() {
//        val target = TapTarget.forView(
//                view,
//                R.id.search,
//                "This is a search icon",
//                "As you can see, it has gotten pretty dark around here..."
//            )
//            .assistant(resources.openRawResource(R.raw.bottom_left))
//            .outerCircleColor(R.color.colorAccent)
//            .targetCircleColor(android.R.color.black)
//            .transparentTarget(true)
//            .textColor(android.R.color.black)
//            .id(2),
    }
}

class Adapter(private val act: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemCount = 20

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.two_line_list_item, parent, false)
        view.setPadding(16, 16, 16, 16)
        return VH(view)
    }

    private fun showVirtualAssistant(target: View) {
        TapTargetView.showFor(
            act,
            TapTarget
                .forView(target, "Hello, Wolrd!", "Beschreibung")
                .transparentTarget(true)
                .outerCircleColor(R.color.colorPrimaryDark)      // Specify a color for the outer circle
                .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                .targetCircleColor(R.color.colorPrimary)   // Specify a color for the target circle
                .titleTextSize(20)                  // Specify the size (in sp) of the title text
                .titleTextColor(android.R.color.white)      // Specify the color of the title text
                .descriptionTextSize(10)            // Specify the size (in sp) of the description text
                .descriptionTextColor(android.R.color.white)  // Specify the color of the description text
                .textColor(android.R.color.white)            // Specify a color for both the title and description text
                .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                .dimColor(android.R.color.black)
                .assistant(target.resources.openRawResource(R.raw.bottom_left))
        )
    }

    override fun getItemCount(): Int = itemCount

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val first = "AOE".repeat(position + 1)
        val second = "OEA".repeat(itemCount - position)
        (holder as VH).bind(first, second)
        holder.itemView.setOnClickListener {
            showVirtualAssistant(holder.firstView)
        }
    }

    class VH(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        val firstView = view.findViewById<TextView>(android.R.id.text1)
        private val secondView = view.findViewById<TextView>(android.R.id.text2)

        fun bind(first: String, second: String) {
            firstView.text = first
            secondView.text = second
        }
    }
}

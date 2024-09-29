package com.pth.androidapp.base.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import com.pth.androidapp.R
import com.pth.androidapp.databinding.BottomNavigationItemBinding

class CustomBottomNavigation @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private var itemClickListener: ((Int) -> Unit)? = null
    private var selectedItemId: Int? = null

    private var selectedColor: Int = ContextCompat.getColor(context, R.color.black)
    private var unselectedColor: Int = ContextCompat.getColor(context, R.color.white)

    init {
        orientation = HORIZONTAL
        attrs?.let {
            val a = context.obtainStyledAttributes(it, R.styleable.CustomBottomNavigation)
            val menuResId = a.getResourceId(R.styleable.CustomBottomNavigation_menu, 0)
            selectedColor =
                a.getColor(R.styleable.CustomBottomNavigation_selectedColor, selectedColor)
            unselectedColor =
                a.getColor(R.styleable.CustomBottomNavigation_unselectedColor, unselectedColor)
            if (menuResId != 0) {
                setMenu(menuResId)
            }
            a.recycle()
        }
    }

    fun setItemClickListener(listener: (Int) -> Unit) {
        this.itemClickListener = listener
    }

    private fun setMenu(menuResId: Int) {
        val popupMenu = PopupMenu(context, null)
        val menu = popupMenu.menu
        val menuInflater = MenuInflater(context)
        menuInflater.inflate(menuResId, menu)

        inflateMenuItems(menu)

        if (childCount > 0) {
            val firstItemId = menu.getItem(0).itemId
            itemClickListener?.invoke(firstItemId)
        }
    }

    private fun inflateMenuItems(menu: android.view.Menu) {
        for (i in 0 until menu.size()) {
            val menuItem = menu.getItem(i)
            addItem(menuItem)
        }
    }

    private fun addItem(menuItem: MenuItem) {
        val binding = BottomNavigationItemBinding.inflate(LayoutInflater.from(context), this, false)

        binding.navIcon.setImageDrawable(menuItem.icon)
        binding.navText.text = menuItem.title

        binding.navIcon.setColorFilter(unselectedColor)
        binding.navText.setTextColor(unselectedColor)
        binding.background.visibility = View.GONE
        binding.background.setBackgroundColor(selectedColor)

        binding.root.tag = menuItem.itemId

        binding.root.setOnClickListener {
            itemClickListener?.invoke(menuItem.itemId)
        }

        addView(binding.root)
    }

    fun updateSelectedItem(itemId: Int) {
        if (selectedItemId == itemId) return

        for (i in 0 until childCount) {
            val itemView = getChildAt(i)
            val binding = BottomNavigationItemBinding.bind(itemView)

            binding.navIcon.setColorFilter(unselectedColor)
            binding.navText.setTextColor(unselectedColor)
            binding.background.visibility = View.GONE
        }

        val selectedItemView = findViewWithTag<View>(itemId)
        selectedItemView?.let {
            val binding = BottomNavigationItemBinding.bind(it)

            binding.navIcon.setColorFilter(selectedColor)
            binding.navText.setTextColor(selectedColor)
            binding.background.visibility = View.VISIBLE
        }

        selectedItemId = itemId
    }
}

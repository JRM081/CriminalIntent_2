package com.example.CriminalIntent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.CriminalIntent.databinding.ListItemCrimeBinding
import com.example.CriminalIntent.databinding.ListItemCrimePoliceRequiredBinding

class CrimeViewHolder(
    private val binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(crime: Crime) {
        when(binding) {
            is ListItemCrimeBinding -> {
                // Handle CrimeHolder binding
                binding.crimeTitle.text = crime.title
                binding.crimeDate.text = crime.date.toString()
            }
            is ListItemCrimePoliceRequiredBinding -> {
                // Handle PoliceRequiredCrimeHolder binding
                binding.crimeTitle.text = crime.title
                binding.crimeDate.text = crime.date.toString()
            }
        }

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}


class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<CrimeViewHolder>() {

    companion object {
        private const val VIEW_TYPE_POLICE_REQUIRED = 0
        private const val VIEW_TYPE_POLICE_NOT_REQUIRED = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice) {
            VIEW_TYPE_POLICE_REQUIRED
        } else {
            VIEW_TYPE_POLICE_NOT_REQUIRED
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : CrimeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_POLICE_REQUIRED -> {
                val binding = ListItemCrimePoliceRequiredBinding.inflate(inflater, parent, false)
                CrimeViewHolder(binding)
            }
            else -> {
                val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
                CrimeViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: CrimeViewHolder, position: Int) {
        val crime = crimes[position]
        holder.bind(crime)
    }
    override fun getItemCount() = crimes.size
}

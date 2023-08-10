package com.example.safetyfirst

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class homeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listMembers = listOf<MemberModel>(
            MemberModel(
                "Himanshu Gupta",
                "New Delhi",
                "69%",
                "46 Kms"
            ),
            MemberModel(
                "Harsh Tiwari",
                "Ghazibad",
                "80%",
                "20 Kms"
            ),
            MemberModel(
                "Kanishka Singh",
                "Bulandshahr",
                "99%",
                "45 Kms"
            ),
            MemberModel(
                "Shambhavi Bhardwaj",
                "Aligarh",
                "23%",
                "124 Kms"
            ),
            MemberModel(
                "Karan Jadaun",
                "Noida",
                "100%",
                "0 Kms"
            ),
        )
        val adapter = MemberAdapter(listMembers)
        val recycler = requireView().findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager= LinearLayoutManager(requireContext())
        recycler.adapter=adapter
    }
    companion object {
        @JvmStatic
        fun newInstance() = homeFragment()
    }
}
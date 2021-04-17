package com.lws.lwsmediaplayer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lws.lwsmediaplayer.databinding.BottomsheetMediaBinding

class MainBottomsheetFragment: BottomSheetDialogFragment() {

    val mediaClickListener: MediaClickListener? = null

    private var _binding: BottomsheetMediaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomsheetMediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlayOrPause.setOnClickListener {
            mediaClickListener?.play()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface MediaClickListener {

        fun play()
    }
}
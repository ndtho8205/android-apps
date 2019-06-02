package edu.ndtho8205.androidtrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import edu.ndtho8205.androidtrivia.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameOverBinding>(
            inflater,
            R.layout.fragment_game_over,
            container,
            false
        )
        binding.btnTryAgain.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                GameOverFragmentDirections.actionGameOverFragmentToGameFragment()
            )
        )

        return binding.root
    }
}

package edu.ndtho8205.androidtrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import edu.ndtho8205.androidtrivia.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameWonBinding>(
            inflater,
            R.layout.fragment_game_won,
            container,
            false
        )

        binding.btnNextMatch.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                GameWonFragmentDirections.actionGameWonFragmentToGameFragment()
            )
        )

        arguments?.let {
            val args = GameWonFragmentArgs.fromBundle(it)
            Toast.makeText(
                context,
                "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestions}",
                Toast.LENGTH_SHORT
            ).show()
        }
        return binding.root
    }
}

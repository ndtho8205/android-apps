package edu.ndtho8205.androidtrivia

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
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

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
        if (getShareIntent()?.resolveActivity(activity!!.packageManager) == null) {
            menu.findItem(R.id.share).isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    private fun getShareIntent(): Intent? {
        arguments?.let {
            val args = GameWonFragmentArgs.fromBundle(it)

//            val shareIntent = Intent(Intent.ACTION_SEND)
//            shareIntent.setType("text/plain").putExtra(
//                Intent.EXTRA_TEXT,
//                getString(R.string.share_success_text, args.numCorrect, args.numQuestions)
//            }
//            return shareIntent

            return ShareCompat.IntentBuilder.from(activity).setText(
                getString(R.string.share_success_text, args.numCorrect, args.numQuestions)
            ).setType("text/plain").intent
        }
        return null
    }
}

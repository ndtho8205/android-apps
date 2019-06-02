package edu.ndtho8205.androidtrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import edu.ndtho8205.androidtrivia.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>

    private val mQuestions: MutableList<Question> = mutableListOf(
        Question(
            text = "What is Android Jetpack?",
            answers = listOf("all of these", "tools", "documentation", "libraries")
        ),
        Question(
            text = "Base class for Layout?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")
        ),
        Question(
            text = "Layout for complex Screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")
        ),
        Question(
            text = "Pushing structured data into a Layout?",
            answers = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")
        ),
        Question(
            text = "Inflate layout in fragments?",
            answers = listOf(
                "onCreateView",
                "onActivityCreated",
                "onCreateLayout",
                "onInflateLayout"
            )
        ),
        Question(
            text = "Build system for Android?",
            answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")
        ),
        Question(
            text = "Android vector format?",
            answers = listOf(
                "VectorDrawable",
                "AndroidVectorDrawable",
                "DrawableVector",
                "AndroidVector"
            )
        ),
        Question(
            text = "Android Navigation Component?",
            answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")
        ),
        Question(
            text = "Registers app with launcher?",
            answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")
        ),
        Question(
            text = "Mark a layout for Data Binding?",
            answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>")
        )
    )

    private lateinit var mBinding: FragmentGameBinding

    private var mQuestionIndex = 0
    private val mNumQuestions = Math.min((mQuestions.size + 1) / 2, 3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        randomizeQuestions()

        mBinding.game = this

        mBinding.btnSubmit.setOnClickListener { submitAnswer(it) }

        return mBinding.root
    }

    private fun submitAnswer(view: View) {
        val checkedId = mBinding.rgQuestion.checkedRadioButtonId
        if (checkedId != -1) {
            val answerIndex = when (checkedId) {
                R.id.rb_second_answer -> 1
                R.id.rb_third_answer -> 2
                R.id.rb_fourth_answer -> 3
                else -> 0
            }

            if (answers[answerIndex] == currentQuestion.answers[0]) {
                mQuestionIndex++
                if (mQuestionIndex < mNumQuestions) {
                    currentQuestion = mQuestions[mQuestionIndex]
                    setQuestion()
                    mBinding.invalidateAll()
                } else {
                    view.findNavController()
                        .navigate(
                            GameFragmentDirections.actionGameFragmentToGameWonFragment(
                                mNumQuestions,
                                mQuestionIndex
                            )
                        )
                }
            } else {
                view.findNavController()
                    .navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment())
            }
        }
    }

    private fun randomizeQuestions() {
        mQuestions.shuffle()
        mQuestionIndex = 0
        setQuestion()
    }

    private fun setQuestion() {
        currentQuestion = mQuestions[mQuestionIndex]
        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.title_android_trivia_question, mQuestionIndex + 1, mNumQuestions)
    }

    data class Question(
        val text: String,
        val answers: List<String>
    )
}

package com.example.assignment2

import android.R.attr.name
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.gridlayout.widget.GridLayout


class GameFragment : Fragment() {

    private lateinit var FIR_board: FourInARow
    var state = GameConstants.PLAYING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_game, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FIR_board = FourInARow()

        var currentState: Int = GameConstants.PLAYING
        val name =
            GameFragmentArgs.fromBundle(requireArguments()).name.replaceFirstChar(Char::uppercase)

        val playerNameView = view.findViewById<TextView>(R.id.playerName)
        playerNameView.text = getString(R.string.player_turn, name)

        val winTextView = view.findViewById<TextView>(R.id.winText)
        winTextView.visibility = View.INVISIBLE

        val gray = ContextCompat.getColor(requireContext(), R.color.gray)

        val gridLayout = view.findViewById<androidx.gridlayout.widget.GridLayout>(R.id.boardGrid)

        for (i in 0 until 6) {
            for (j in 0 until 6) {
                val button = Button(context)
                button.id = i * 6 + j
                button.layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i, 1f)
                    columnSpec = GridLayout.spec(j, 1f)
                    width = 0
                    height = 0
                    rightMargin = 2
                    leftMargin = 2
                    topMargin = 2
                    bottomMargin = 2

                }
                button.backgroundTintList = ColorStateList.valueOf(gray)
                button.setOnClickListener { view ->
                    if (state == GameConstants.PLAYING) {
                        gameUpdate(button, name)
                    }
                }
                gridLayout.addView(button)
            }
        }
    }

    fun gameUpdate(view: View, name: String): Int {
        val name =
            GameFragmentArgs.fromBundle(requireArguments()).name.replaceFirstChar(Char::uppercase)

        val playerNameView = view.rootView.findViewById<TextView>(R.id.playerName)
        val compNameView = view.rootView.findViewById<TextView>(R.id.compName)
        val resetButton = view.rootView.findViewById<Button>(R.id.reset)

        val red = ContextCompat.getColor(requireContext(), R.color.red)
        val blue = ContextCompat.getColor(requireContext(), R.color.blue)
        val gray = ContextCompat.getColor(requireContext(), R.color.gray)

        if (view.backgroundTintList?.defaultColor == gray) {
            view.backgroundTintList = ColorStateList.valueOf(blue)
            FIR_board.setMove(GameConstants.BLUE, view.id)
            if (FIR_board.checkForWinner() != -1) {
                playerNameView.text = getString(R.string.player_win, name)
                resetButton.visibility = View.VISIBLE
                state = FIR_board.checkForWinner()
                return FIR_board.checkForWinner()


            }

            playerNameView.visibility = View.INVISIBLE
            compNameView.visibility = View.VISIBLE

            val comp = FIR_board.computerMove

            FIR_board.setMove(GameConstants.RED, comp)
            val compView =
                view.rootView.findViewById<View>(R.id.boardGrid).findViewById<Button>(comp)

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                compView.backgroundTintList = ColorStateList.valueOf(red)
                playerNameView.visibility = View.VISIBLE
                compNameView.visibility = View.INVISIBLE
            }, 1200)

            FIR_board.checkForWinner()
            if (FIR_board.checkForWinner() != -1) {
                playerNameView.text = getString(R.string.computer_win)
                handler.postDelayed({
                    resetButton.visibility = View.VISIBLE
                }, 1200)
                    state = FIR_board.checkForWinner()
                return FIR_board.checkForWinner()
            }
        }


        resetButton.setOnClickListener {

            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            requireActivity().finish()

        }

        return -1
    }
}


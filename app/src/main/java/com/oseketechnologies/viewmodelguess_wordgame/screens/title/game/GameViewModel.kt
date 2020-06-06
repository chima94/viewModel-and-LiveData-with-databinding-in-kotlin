package com.oseketechnologies.viewmodelguess_wordgame.screens.title.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class GameViewModel : ViewModel() {
    companion object{
        // Time when the game is over
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the game
        private const val COUNTDOWN_TIME = 60000L
    }

    private var timer : CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    val currentTime : LiveData<Long>
        get() = _currentTime

    private val _word = MutableLiveData<String>()
    val word : LiveData<String>
        get() = _word

    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score

    private val _isListEmpty = MutableLiveData<Boolean>()
    val isListEmpty : LiveData<Boolean>
        get() = _isListEmpty

    private lateinit var wordList : MutableList<String>

    val CurrentTimeString = Transformations.map(currentTime){
        time -> DateUtils.formatElapsedTime(time)
    }
    val wordHint = Transformations.map(word){hintword->
         val randomPostion = (1..hintword.length).random()
        "The current word has "+hintword.length+" letters"+
                "\nThe letter at position "+randomPostion+ " is "+
                hintword.get(randomPostion - 1).toUpperCase()
    }

    init{
        Log.i("GameViewModel", "GameViewModel Created")
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onFinish() {
                _currentTime.value = DONE
                finishedWord()
            }

            override fun onTick(p0: Long) {
                _currentTime.value = p0 / ONE_SECOND
            }
        }
        timer.start()
        _word.value = ""
        _score.value = 0
        resetList()
        nextWord()
    }

    fun resetList(){
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

     fun Onskip(){
        if(score.value == 0){
            _score.value = 0
        }else{
            _score.value = (score.value)?.minus(1)
        }

        nextWord()
    }

    fun OnCorrect(){
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

     fun nextWord(){
        if(!wordList.isEmpty()){
            _word.value = wordList.removeAt(0)
        }else{
            resetList()
        }
    }


     fun finishedWord(){
        _isListEmpty.value = true
    }

    fun onGameFinishComplete(){
        _isListEmpty.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

}
package com.example.todolistapp

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Entity(tableName = "task_item_table")
class TaskItem(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "desc") var desc: String,
    @ColumnInfo(name = "dueTimeString") var dueTimeString: String?,
    @ColumnInfo(name = "completedDateString") var completedDateString: String?,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)
{

    private fun completedDate(): LocalDate? = if (completedDateString == null) null else LocalDate.parse(completedDateString, dateFormatter)
    fun dueTime(): LocalTime? = if (dueTimeString == null) null else LocalTime.parse(dueTimeString, timeFormatter)

    fun isCompleted() = completedDate() != null
    fun imageResource(): Int = if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
    fun imageColor(context: Context): Int = if(isCompleted()) purple(context) else black(context)

    private fun purple(context: Context) = ContextCompat.getColor(context, R.color.black)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)

    companion object {
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_TIME
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}

/*
@Entity(tableName = "task_item_table")
class TaskItem(

    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "desc") var desc: String,
    @ColumnInfo(name = "dueTimeString")var dueTimeString: String?,
    @ColumnInfo(name = "completedDateString") var completedDateString: String?,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
){

    private fun completedDate(): LocalDate? = if (completedDateString == null) null
        else LocalDate.parse(completedDateString, dateFormatter)
    fun dueTime(): LocalTime? = if(dueTimeString == null) null
        else LocalTime.parse(dueTimeString, timeFormatter)


    fun isCompleted() = completedDate()!= null
    fun imageResource(): Int = if (isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
    fun imageColor(context: Context): Int = if(isCompleted()) purple(context) else black(context)

    private fun purple(context: Context) = ContextCompat.getColor(context, androidx.appcompat.R.color.material_grey_50);
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black);

    companion object{
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}*/

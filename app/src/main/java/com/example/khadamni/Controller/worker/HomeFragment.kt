package com.example.khadamni.Controller.worker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.khadamni.R
import com.example.khadamni.models.User
import com.example.khadamni.models.UsersAndMessage
import com.example.khadamni.services.ApiUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class HomeFragment : Fragment() {
    lateinit var recylcerWorker: RecyclerView
    lateinit var recylcerWorkerAdapter: WorkerAdapter
    var usersList : MutableList<User> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        recylcerWorker = rootView.findViewById(R.id.recyclerWorkers)
        var WorkersList : MutableList<User> = ArrayList()






      /*  WorkersList.add(Worker("1","Name : Belgassem","Role : Najar","Location : Ben Arous",R.drawable.juka))
        WorkersList.add(Worker("1","Name : Amor","Role : ingenieur","Location : Megrine",R.drawable.saif))
        WorkersList.add(Worker("1","Name : Salah","Role : Baney","Location : Tunis",R.drawable.saif))
        WorkersList.add(Worker("1","Name : Mohamed","Role : Baney","Location : Rades",R.drawable.juka))
        WorkersList.add(Worker("1","Name : Amir","Role : Dahen","Location : Rades",R.drawable.juka))*/

        doLogin()

        recylcerWorkerAdapter = WorkerAdapter(usersList)
        recylcerWorker.adapter = recylcerWorkerAdapter
        recylcerWorker.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)

        return rootView

    }
    private  fun doLogin(){
        print("aaaaaaaaaaaaaaaaaaaaaaaaaaa")
        val apiInterface = ApiUser.create()

      /*  if (apiInterface.isSuccessful && apiInterface.body() != null) {
            val content = apiInterface.body()
            usersList= content!!
            recylcerWorkerAdapter = WorkerAdapter(usersList)
            recylcerWorker.adapter = recylcerWorkerAdapter
        } else {
            Toast.makeText(context,
                "Error Occurred: ${apiInterface.message()}",
                Toast.LENGTH_LONG
            ).show()
        }*/

            apiInterface.getUsers().enqueue(object : Callback<UsersAndMessage> {
                override fun onResponse(call: Call<UsersAndMessage >, response: Response<UsersAndMessage>) {
                    val usersList : MutableList<User>?
                     usersList = response.body()!!.users
                    print("i m list of users"+usersList);

                    if (usersList != null) {
                        print("aaaaaa"+usersList)
                        recylcerWorkerAdapter = WorkerAdapter(usersList)
                        recylcerWorker.adapter = recylcerWorkerAdapter

                    }



                }

                override fun onFailure(call: Call<UsersAndMessage>, t: Throwable) {
                    print("kberna w ma tbadalnesh")
                }


            })


    }

}


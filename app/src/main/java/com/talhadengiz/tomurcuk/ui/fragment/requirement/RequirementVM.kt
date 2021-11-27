package com.talhadengiz.tomurcuk.ui.fragment.requirement

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.talhadengiz.tomurcuk.data.model.Requirement

class RequirementVM : ViewModel() {
    private var firestore: FirebaseFirestore? = null
    var isSuccess = MutableLiveData<Boolean>()

    init {
        firestore = FirebaseFirestore.getInstance()
    }

    fun saveRequirement(title: String, total: String, location: String) {
        val requirement = Requirement(title = title, total = total, location = location)
        firestore?.collection("requirement")?.document(title)?.set(requirement)?.addOnSuccessListener {
            isSuccess.postValue(true)
        }?.addOnFailureListener {
            Log.d("Hata",it.localizedMessage)
        }

    }
}
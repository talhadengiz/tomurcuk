package com.talhadengiz.tomurcuk.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.talhadengiz.tomurcuk.data.model.Requirement
import com.talhadengiz.tomurcuk.utils.Constant

class RequirementVM : ViewModel() {
    private var firestore: FirebaseFirestore? = null
    var isSuccess = MutableLiveData<Boolean>()
    private var _requirements = MutableLiveData<List<Requirement>>()
    val requirements: LiveData<List<Requirement>> get() = _requirements

    init {
        firestore = FirebaseFirestore.getInstance()
        getData()
    }

    private fun getData() {
        firestore?.collection(Constant.collectionName)?.whereEqualTo(Constant.fieldName, false)
            ?.addSnapshotListener { value, error ->
                value?.toObjects(Requirement::class.java).let {
                    _requirements.postValue(it)
                }
            }
    }

    fun saveRequirement(title: String, total: String, location: String) {
        val requirement = Requirement(title = title, total = total, location = location)
        firestore?.collection(Constant.collectionName)?.document(title)?.set(requirement)
            ?.addOnSuccessListener {
                isSuccess.postValue(true)
            }?.addOnFailureListener {
                Log.d(Constant.errorTag, it.localizedMessage)
            }

    }
}
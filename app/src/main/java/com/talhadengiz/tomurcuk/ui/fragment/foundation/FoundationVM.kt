package com.talhadengiz.tomurcuk.ui.fragment.foundation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.talhadengiz.tomurcuk.data.model.Requirement
import com.talhadengiz.tomurcuk.utils.Constant

class FoundationVM : ViewModel() {
    private var firestore: FirebaseFirestore? = null

    private var _requirements = MutableLiveData<List<Requirement>>()
    val requirements: LiveData<List<Requirement>> get() = _requirements

    init {
        firestore = FirebaseFirestore.getInstance()
        getData()
    }

    fun update(title: String) {
        firestore?.collection(Constant.collectionName)?.document(title)
            ?.update(Constant.fieldName, true)
    }

    private fun getData() {
        firestore?.collection(Constant.collectionName)?.whereEqualTo(Constant.fieldName, false)
            ?.addSnapshotListener { value, error ->
                value?.toObjects(Requirement::class.java).let {
                    _requirements.postValue(it)
                }
            }
    }

}
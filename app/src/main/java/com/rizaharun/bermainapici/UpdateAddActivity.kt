package com.rizaharun.bermainapici

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rizaharun.bermainapici.model.DataItem
import com.rizaharun.bermainapici.presenter.CrudView
import com.rizaharun.bermainapici.presenter.Presenter
import kotlinx.android.synthetic.main.activity_update_add.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

@Suppress("SENSELESS_COMPARISON")
class UpdateAddActivity : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_add)
        presenter = Presenter(this)
        val itemDataItem = intent.getSerializableExtra("dataItem")
        if (itemDataItem == null){
            btnAction.text = "Add"
            btnAction.onClick {

                val name: String = etName.text.toString()
                val phone: String = etPhone.text.toString()
                val alamat: String = etAlamat.text.toString()

                if (name.isNullOrEmpty()){
                    //jika form Nama belum di isi / masih kosong
                    etName.error="Name field is required"
                    etName.requestFocus()
                }else if (phone.isNullOrEmpty()){
                    //jika form No HP belum di isi / masih kosong
                    etPhone.error="Phone field is required"
                    etPhone.requestFocus()
                }else if (alamat.isNullOrEmpty()){
                    //jika form Alamat belum di isi / masih kosong
                    etAlamat.error="Address field is required"
                    etAlamat.requestFocus()
                }else{
                    presenter.addData(
                        etName.text.toString(),
                        etPhone.text.toString(),
                        etAlamat.text.toString())
                    //jika form sudah terisi semua
                    Toast.makeText(applicationContext, "Success Add Data", Toast.LENGTH_SHORT).show()
                }
            }
        }else if (itemDataItem != null){
            btnAction.text = "Update"
            val item = itemDataItem as DataItem?
            etName.setText(item?.staffName.toString())
            etPhone.setText(item?.staffHp.toString())
            etAlamat.setText(item?.staffAlamat.toString())
            btnAction.onClick {

                val name: String = etName.text.toString()
                val phone: String = etPhone.text.toString()
                val alamat: String = etAlamat.text.toString()

                if (name.isNullOrEmpty()){
                    //jika form Nama belum di isi / masih kosong
                    etName.error="Name field is required"
                    etName.requestFocus()
                }else if (phone.isNullOrEmpty()){
                    //jika form No HP belum di isi / masih kosong
                    etPhone.error="Phone field is required"
                    etPhone.requestFocus()
                }else if (alamat.isNullOrEmpty()){
                    //jika form Alamat belum di isi / masih kosong
                    etAlamat.error="Address field is required"
                    etAlamat.requestFocus()
                }else{
                    presenter.updateData(
                        item?.staffId ?: "",
                        etName.text.toString(),
                        etPhone.text.toString(),
                        etAlamat.text.toString())

                    //jika form sudah terisi semua
                    Toast.makeText(applicationContext, "Success Update Data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun successAdd(msg: String) {
        startActivity<MainActivity>()
        finish()
    }
    override fun errorAdd(msg: String) {}
    override fun onSuccessUpdate(msg: String) {
        startActivity<MainActivity>()
        finish()
    }
    override fun onErrorUpdate(msg: String) {}
    override fun onSuccessGet(data: List<DataItem>?) {}
    override fun onFailedGet(msg: String) {}
    override fun onSuccessDelete(msg: String) {}
    override fun onErrorDelete(msg: String) {}
}


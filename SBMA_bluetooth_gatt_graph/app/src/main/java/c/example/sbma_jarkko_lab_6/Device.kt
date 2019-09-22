package c.example.sbma_jarkko_lab_6

import java.io.Serializable

class Device(val name: String?, val address: String, var power: Int, var isConnectable: Boolean): Serializable {

}
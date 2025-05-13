package aibytests

import org.apache.commons.math3.util.MathArrays
import org.slf4j.LoggerFactory

abstract class UnitTest {
    protected val log = LoggerFactory.getLogger(UnitTest::class.java)

    fun similarity(a: List<Double>, b: List<Double>): Double {
        return MathArrays.cosAngle(a.toDoubleArray(), b.toDoubleArray())
    }

    fun log(message: Any) {
        log.debug(message.toString())
    }
}
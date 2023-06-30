package cl.scotiabank.libraries.jetpackcomposeinstagram

import java.util.Scanner

/**
 * Longest Word
 *  Have the function LongestWord(sen) take the sen parameter being passed and return the longest word in the string.
 *  If there are two or more words that are the same length, return the first word from the string with that length.
 *  Ignore punctuation and assume sen will not be empty. Words may also contain numbers, for example "Hello world123 567"
 *
 *  Examples
 *      Input: "fun&!! time"
 *      Output: time
 *
 *      Input: "I love dogs"
 *      Output: love
 */

fun longestWord(sen: String): String {
    val array = sen.split(" ")
    var resultMap = hashMapOf<Int, String>()
    var lastWordLength = 0
    array.forEach { value ->
        println("value: $value")
        if (resultMap.isEmpty() && value.length > 1) {
            lastWordLength = value.length
            resultMap[lastWordLength] = value
        }
        val lengthWord = value.length
        if (lastWordLength > lengthWord) {
            lastWordLength = lengthWord
            resultMap[lengthWord] = value
        }
    }
    return resultMap[lastWordLength].toString();
}

fun longestWordBySolutions(sen: String): String {
    var pos = 0
    var buildingword = ""
    var longestWord = ""
    while (pos < sen.length) {
        println("while: ${sen[pos]}")
        if (sen[pos] in 'a'..'z' || sen[pos] in 'A'..'Z' || sen[pos].isDigit()) {
//            println("character: ${sen[pos]}")
            buildingword += sen[pos]
            println("buildingWord: $buildingword")
            if (pos + 1 == sen.length) {
                if (buildingword.length > longestWord.length) {
                    longestWord = buildingword
                }
            }
        } else {
            println("--------else---------: ${sen[pos]}")
            if (buildingword.length > longestWord.length) {
                longestWord = buildingword
                buildingword = ""
            } else if (buildingword.length <= longestWord.length) {
                buildingword = ""
            }
        }
        pos++
    }
    return longestWord
}

fun main() {
    val input1 = "fun&!! time"
    val input2 = "I love dogs"
    println("result: " + longestWordBySolutions(input1))
}


/**
 * Two Sum
 *     Given an array of integers nums and an integer target, return indices of
 *     the two numbers such that they add up to target.
 *     You may assume that each input would have exactly one solution,
 *     and you may not use the same element twice.
 *     You can return the answer in any order.
 *     Example 1:
 *          Input: nums = [2,7,11,15], target = 9
 *          Output: [0,1]
 *          Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *     Example 2:
 *          Input: nums = [3,2,4], target = 6
 *          Output: [1,2]
 *     Example 3:
 *          Input: nums = [3,3], target = 6
 *          Output: [0,1]
 */

internal fun twoSum(nums: IntArray, target: Int): IntArray {
//    Input: nums = [2,7,11,15], target = 9
//    Output: [0,1]

    var resultArray = IntArray(2) { 0 }
    var i = 0
    var j = 0
    while (i < nums.size) {
        var indice = (j + (i + 1))
        while (indice < nums.size) {
            val suma = nums[i] + nums[indice]
            if ((nums[i] + nums[indice]) == target) {
                resultArray[0] = i
                resultArray[1] = indice
            }
            indice++
        }
        j = 0
        i++
    }

    println("first position: ${resultArray[0]}")
    println("second position: ${resultArray[1]}")
    return resultArray
}

internal fun twoSumSolutionsSuggest(nums: IntArray, target: Int): IntArray {
    val map = HashMap<Int, Int>()
    val result = IntArray(2)
    for (i in nums.indices) {
        val complement = target - nums[i]
        println("i: $i")
        println("   complement: $complement")
        if (map.containsKey(complement)) {
            result[0] = map[complement]!!
            result[1] = i
            return result
        }
        map[nums[i]] = i
    }
    return result
}

fun mainTwoSum() {
    val arrayOf = intArrayOf(2, 7, 11, 15)
    val target = 9

//    println(twoSum(nums = arrayOf, target = target))
    println(twoSumSolutionsSuggest(nums = arrayOf, target = target))
}


/**
 * Ejercicio:
 * Complete the function solveMeFirst to compute the sum of two integers.
 */

internal fun solveMeFirst(a: Int, b: Int) = a + b

fun mainFindIntersection(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val num1 = sc.nextInt()
    val num2 = sc.nextInt()
    val sum = solveMeFirst(num1, num2)
    println(sum)
}


/**
 * Ejercicio:
 *
 * Find Intersection
 *      Have the function FindIntersection(strArr) read the array of strings stored in strArr which will contain 2 elements:
 *      the first element will represent a list of comma-separated numbers sorted in ascending order, the second element will
 *      represent a second list of comma-separated numbers (also sorted). Your goal is to return a comma-separated string containing
 *      the numbers that occur in elements of strArr in sorted order. If there is no intersection, return the string false.
 *
 *      Examples
 *          Input: arrayOf("1, 3, 4, 7, 13", "1, 2, 4, 13, 15")
 *          Output: 1,4,13
 *          Input: arrayOf("1, 3, 9, 10, 17, 18", "1, 4, 9, 10")
 *          Output: 1,9,10
 */
internal fun findIntersection(strArr: Array<String>): List<String> {

    val list1 = strArr[0].split(",").map { it.trim() }
    val list2 = strArr[1].split(",").map { it.trim() }
    val result = arrayListOf<String>()

    list1.forEach { value1 ->
        list2.forEach { value2 ->
            if (value1 == value2) {
                result.add(element = value1)
            }
        }
    }
    return result
}

/**
 * solución con mejor puntuación
 */
internal fun FindIntersection(strArr: Array<String>): String {
    val firstArrayStr = strArr[0]
    val secondArrayStr = strArr[1]

    val arr1 = firstArrayStr.split(", ").map { it.toInt() }
    val arr2 = secondArrayStr.split(", ").map { it.toInt() }

    var i = 0
    var j = 0
    var res = ""

    while (i < arr1.size && j < arr2.size) {
        // when equal
//         arrayOf("1, 3, 4, 7, 13", "1, 2, 4, 13, 15")

        if (arr1[i] == arr2[j]) {
            res = res + arr1[i] + ","
            i++
            j++
            continue
        }

        if (arr1[i] < arr2[j]) {
            i++
            continue
        }

        j++
    }

    if (res.isEmpty()) {
        return "false"
    }

    return res.substring(0, res.length - 1)
}


fun mainFindIntersection() {
    val arrayOf = arrayOf("1, 3, 4, 7, 13", "1, 2, 4, 13, 15")
    println(findIntersection(arrayOf))
}

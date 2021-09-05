package com.sixbits.assessment.core.mapper

interface Mapper<Input, Output> {
    fun map(input: Input): Output
}

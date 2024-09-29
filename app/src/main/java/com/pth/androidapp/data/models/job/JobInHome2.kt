package com.pth.androidapp.data.models.job

import com.pth.androidapp.data.models.employer.Employer

data class JobInHome2(
    val id: String?,
    val image: String?,
    val name: String?,
    val created: String?,
    val toDate: String?,
    val employer: Employer?
)

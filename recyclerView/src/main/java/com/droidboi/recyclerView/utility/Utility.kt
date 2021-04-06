package com.droidboi.recyclerView.utility

import androidx.recyclerview.widget.RecyclerView

/**
 * Type-Alias as a General Purpose event notifier from [RecyclerView.ViewHolder]
 * to the [RecyclerView.Adapter].
 *
 * @author Ritwik Jamuar
 */
typealias ViewHolderListener = (Int) -> Unit

package com.example.booklibrary.ui.barcode

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.scanbot.sdk.ui_v2.barcode.BarcodeScannerView
import io.scanbot.sdk.ui_v2.barcode.common.mappers.COMMON_CODES
import io.scanbot.sdk.ui_v2.barcode.configuration.BarcodeFormat
import io.scanbot.sdk.ui_v2.barcode.configuration.BarcodeNativeConfiguration
import io.scanbot.sdk.ui_v2.barcode.configuration.BarcodeScannerConfiguration
import io.scanbot.sdk.ui_v2.barcode.configuration.LocalBarcodeNativeConfiguration
import io.scanbot.sdk.ui_v2.barcode.configuration.SingleScanningMode
import io.scanbot.sdk.ui_v2.common.ScanbotColor

@Composable
fun BookISBNScanner(onSuccessfulScan: (String) -> Unit, onBarcodeScannerClosed: () -> Unit) {
    var configuration by remember { mutableStateOf<BarcodeScannerConfiguration?>(null) }
    Scaffold { innerPadding ->
        if (configuration == null) {
            innerPadding
            configuration = BarcodeScannerConfiguration().apply {
                this.topBar.backgroundColor = ScanbotColor("#B692EE")
                this.useCase = SingleScanningMode().apply {
                    this.confirmationSheetEnabled = true
                    this.sheetColor = ScanbotColor("#FFFFFF")

                    this.barcodeImageVisible = true

                    this.barcodeTitle.visible = true
                    this.barcodeTitle.color = ScanbotColor("#000000")

                    this.barcodeSubtitle.color = ScanbotColor("#000000")

                    this.cancelButton.text = "Close"
                    this.cancelButton.foreground.color = ScanbotColor("#834EFF")
                    this.cancelButton.background.fillColor = ScanbotColor("#00000000")

                    this.submitButton.text = "Submit"
                    this.submitButton.foreground.color = ScanbotColor("#FFFFFF")
                    this.submitButton.background.strokeColor = ScanbotColor("#834EFF")
                    this.submitButton.background.fillColor = ScanbotColor("#834EFF")


                }

                this.recognizerConfiguration.barcodeFormats = BarcodeFormat.COMMON_CODES
            }
        }
    }

    configuration?.let {
        CompositionLocalProvider(
            LocalBarcodeNativeConfiguration provides BarcodeNativeConfiguration(
                enableContinuousScanning = true
            )
        ) {
            BarcodeScannerView(
                configuration = it,
                onBarcodeScanned = { result ->
                    val barcodeItem = result.items.first()
                    configuration = null
                    onSuccessfulScan(barcodeItem.text)

                },
                onBarcodeScannerClosed = {
                    configuration = null
                    onBarcodeScannerClosed()
                }
            )
        }
    }
}
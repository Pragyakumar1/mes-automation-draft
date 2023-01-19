Feature: Chip planning and synthesis admin

  @E2E_Upto_PCA_And_ECR
  Scenario: Gene fragment with adaptors up to PCA process
    Given user has create construct request with
      | type        | NON_CLONED_GENE |
      | adapters_on | true            |
    When user invoke create construct API
    Then verify 201 response
    Given user has create quote request
      | type                   | NA |
      | order_sub_product_type | NA |
    When user invoke create quote API
    Then verify 201 response
    Then verify the quote status is "SUCCESS"

    Given user has create order request
    When user invoke create order API

    Then user sync order to MES from SFDC
    When user invoke get order-items details and check sync happened at MES

    And user fetch order details
    And user create chip plan from UI
    And user navigate to synthesis admin for SRN details
    Given user has create synthesis run request
    When user invoke create synthesis run api
    #------------
    Given user has created request for add started event
    When user invoke create add started event api
   # Then verify started event has successfully created

    Given user has created request for layer completion
    When user invoke create layer completion api

    Given user has created request for add completed event
    When user invoke for create add completed event

    When user invoke get ready plates for CHP Deprotection
    Then user should able to perform chp deprotection submission

    Given user invoke get plate number api
    When user has created request for generate extraction plate bar code
    Then user invoke generate extraction plate api
    And User has moved the plate from transportation to done lane
#----
    Given operator login to sample tracker
    And operator submit "PCA Planning"
    And operator submit "PCA Master Mix Creation"
    And operator submit "PCA Primer Hitpicking Create Source"
    And operator submit "PCA Master Mix Addition"
    And operator submit "PCA Oil Addition"
    And operator submit "PCA Thermocycling"
    And operator submit "PCA PCR Master Mix Creation"
    And operator submit "PCA PCR Primer Hitpicking"
    And operator submit "PCA PCR Master Mix Addition"
    And operator submit "PCA PCR Thermocycling"
    And operator submit "PCA PCR Purification"
    And operator submit "PCA PCR Aliquoting for Quant"
    And operator submit "PCA PCR Upload Quant Data"
    And operator QC review and release barcode
    And operator submit "PCA PCR Normalization"

    And operator submit "ECR Annealing Buffer Addition"
    And operator submit "ECR Denaturation Reannealing"
    And operator submit "ECR PCR Planning"
    And operator submit "ECR CyBio Dispense"
    And operator submit "ECR Echo Enzyme Addition"
    And operator submit "ECR Thermocycling"
    And operator submit "ECR PCR Primer Hitpicking Create Source"
    And operator submit "ECR PCR Master Mix Creation"
    And operator submit "ECR PCR Primer Hitpicking Download WL"
    And operator submit "ECR PCR Master Mix Addition"
    And operator submit "ECR PCR Primer Hitpicking"
    And operator submit "ECR PCR Thermocycling"
    And operator submit "ECR PCR Purification"
    And operator submit "ECR PCR Aliquoting for Quant"
    And operator submit "ECR PCR Upload Quant Data"
    And operator QC review and release ECR barcode
    And operator submit "ECR PCR Stamp into Echo Plate"

  @Create_ChipPlan
  Scenario Outline: create chip plan and move order to done in synthesis admin
    Given user has existing order details
      | sfdc_id   |
      | <sfdc_id> |
    And user fetch order details
    And user create chip plan from UI

    And user navigate to synthesis admin for SRN details
    Given user has create synthesis run request
    And user invoke create synthesis run api
    Given user has created request for add started event
    And user invoke create add started event api
    Given user has created request for layer completion
    And user invoke create layer completion api
    Given user has created request for add completed event
    And user invoke for create add completed event
    And user invoke get ready plates for CHP Deprotection
    Then user should able to perform chp deprotection submission
    And user invoke get plate number api
    And user has created request for generate extraction plate bar code
    And user invoke generate extraction plate api

    Then User has moved the plate from transportation to done lane
    Examples:
      | sfdc_id                               |
      |8017500000003jqAAA                     |


  Scenario: PCA operations with barcode
    Given operator login to sample tracker
    And operator has chip barcode file "barcodes.yml"
    And operator submit "PCA Planning"
    And operator submit "PCA Master Mix Creation"
    And operator submit "PCA Primer Hitpicking Create Source"
    And operator submit "PCA Master Mix Addition"
    And operator submit "PCA Oil Addition"
    And operator submit "PCA Thermocycling"
    And operator submit "PCA PCR Master Mix Creation"
    And operator submit "PCA PCR Primer Hitpicking"
    And operator submit "PCA PCR Master Mix Addition"
    And operator submit "PCA PCR Thermocycling"
    And operator submit "PCA PCR Purification"
    And operator submit "PCA PCR Aliquoting for Quant"
    And operator submit "PCA PCR Upload Quant Data"
    And operator QC review and release barcode
    And operator submit "PCA PCR Normalization"

  Scenario: ECR operations with barcode for Clonal-Gene
    Given operator login to sample tracker
    And operator has chip barcode
      | ChipBarcode        |
      | pPPRn1026B483843B1 |
    And operator submit "ECR Annealing Buffer Addition"
    And operator submit "ECR Denaturation Reannealing"
    And operator submit "ECR PCR Planning"
    And operator submit "ECR CyBio Dispense"
    And operator submit "ECR Echo Enzyme Addition"
    And operator submit "ECR Thermocycling"
    And operator submit "ECR PCR Primer Hitpicking Create Source"
    And operator submit "ECR PCR Master Mix Creation"
    And operator submit "ECR PCR Primer Hitpicking Download WL"
    And operator submit "ECR PCR Master Mix Addition"
    And operator submit "ECR PCR Primer Hitpicking"
    And operator submit "ECR PCR Thermocycling"
    And operator submit "ECR PCR Purification"
    And operator submit "ECR PCR Aliquoting for Quant"
    And operator submit "ECR PCR Upload Quant Data"
    And operator QC review and release ECR barcode
    And operator submit "ECR PCR Stamp into Echo Plate"


  Scenario: PLS operations with barcode
    Given operator login to sample tracker
    And operator has chip barcode file "barcodes.yml"
    And operator submit "PLS Planning"
    And operator submit "PLS Normalization and Reformatting"
    And operator submit "PLS Vector Hitpicking Create Source"
    And operator submit "PLS Vector Plate Retrieval"
    And operator submit "PLS Vector Plate Build"
    And operator submit "PLS Tube Placement Confirmation"
    And operator submit "PLS Hamilton Vector Source Plate Creation"
    And operator submit "PLS Drydown In"
    And operator submit "PLS Drydown Out"
    And operator submit "PLS Vector Hitpicking"
    And operator submit "PLS Master Mix Creation"
    And operator submit "PLS Master Mix Addition"
    And operator submit "PLS Thermocyling"
    And operator submit "PLS Dilution"

    And operator submit "VIN Register Vector To Tubes"
    And operator submit "VIN Register Tubes to Plate"
    And operator submit "VIN Register Plate to Storage"

  Scenario: RCA operations with barcode
    Given operator login to sample tracker
    And operator has chip barcode file "barcodes.yml"
#    And operator submit "RCA SUP Centrifugation"
#    And operator submit "RCA Resuspension"
#    And operator submit "RCA SUP Plate Consolidation"
#    And operator submit "RCA Aliquoting"
#    And operator submit "RCA Pellet Centrifugation"
#    And operator submit "RCA Cell Dilution on Cybio"
#    And operator submit "RCA Boiling"
#    And operator submit "RCA Cell and Reagent Addition"
#    And operator submit "RCA Incubation Start"
#    And operator submit "RCA Incubation End"
#    And operator submit "RCA Denaturation"
#    And operator submit "RCA First Dilution on Multiflo"
    And operator submit "RCA Cybio Dilution"


    # Order which contains cluster count<384 it will go to TEP Pooling on Hamilton
    # If number of Sample <175 cluster count then it will generate BNDH barcode
    # If number of sample >=175 cluster count then it will generate FLP Barcode
  Scenario: TEA operations from Re-Suspension to TEP Pooling On Hamilton
    Given operator login to sample tracker
    And operator has chip barcode
      |ChipBarcode|
#      |pCHPx0802B255216QE|
    |pCHPx1130B394301ab|
    Given User invoked plate info api
    And operator submit "TEA Re Suspension"
    And operator submit "TEA Create Daughter Plates"
    And operator submit "TEA MM Creation"
    And operator submit "TEA PCR Master Mix Dispensing"
    And operator submit "TEA PCR Plate Stamping"
    And operator submit "TEA PCR Plate Thermocycling"
    And operator submit "TEP Pooling Planning"
#    And operator submit "TEP Pooling on Hamilton"
#    And operator submit "TEA Retention"
#    And operator submit "TEA Revival"


    # Order which contains cluster count>=384 it will go to TEP Pooling on Hamilton
  Scenario: TEA operations from Re-Suspension to TEP Pooling On FlipJig
    Given operator login to sample tracker
    And operator has chip barcode
      |ChipBarcode|
#      |pCHPx0802B255216QE|
      |6704PCR01I9R01|
    Given User invoked plate info api
#    And operator submit "TEA Re Suspension"
#    And operator submit "TEA Create Daughter Plates"
#    And operator submit "TEA MM Creation"
#    And operator submit "TEA PCR Master Mix Dispensing"
#    And operator submit "TEA PCR Plate Stamping"
#    And operator submit "TEA PCR Plate Thermocycling"
#    And operator submit "TEP Pooling Planning"
#    And operator submit "TEP Pooling on Hamilton"
#    And operator submit "TEA Retention"
#    And operator submit "TEA Revival"


  Scenario: Glycerol reorder operations
    Given Operator login to SFDC
    Given operator has sfdcid
      |sfdcId|
      |8016C000000YfKiQAK|
    Then user open orders on SFDC
    When user invoke get order-items details and check sync happened at MES

  Scenario: CLO operations with barcode
    Given operator login to sample tracker
    And operator has chip barcode
      | ChipBarcode        |
      | pPLSi1019B853547QG |
#    And operator submit "CLO Transformation"
    And operator submit "CLO Plating Planning"
#    And operator submit "CLO Plating on Hamilton"

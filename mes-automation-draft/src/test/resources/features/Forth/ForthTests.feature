Feature: NGS Forth

  @Forth @NGSKit
  Scenario Outline: process forth packing and booking for NGS Kit

    And user test order packing and booking from Forth UI
      | wor_id   | sfdc_id   |
      | <wor_id> | <sfdc_id> |
    And user fetch order details

    Given user has create invoice request with status "BOOKED"
    When user invoke invoice API
    Then verify 200 response

    Given user has create invoice request with status "IN_TRANSIT"
    When user invoke invoice API
    Then verify 200 response

    And user downloaded the invoice

    Examples:
      | wor_id                       | sfdc_id            |
      | WOR_611d22d00087460007365c62 | 8016C000000YYqjQAG |

  @Forth
  Scenario Outline: place order for Gene fragment with adaptors
    Given user has existing order details
      | sfdc_id   |
      | <sfdc_id> |

    Given user has create inventory request for "shippable_fragment"
    When user invoke create inventory API
    Then verify 200 response

    Given user has create shipment request for "shippable_fragment"
    When user invoke create shipment API
    Then verify 200 response

    Given user has finalize shipment request
    When user invoke finalize shipment API
    Then verify 200 response

    Given user has create invoice request with status "BOOKED"
    When user invoke invoice API
    Then verify 200 response

    Given user has create invoice request with status "IN_TRANSIT"
    When user invoke invoice API
    Then verify 200 response

    Examples:
      | sfdc_id            |
      | 8016C000000YYUWQA4 |

  @Forth @GeneFragmentWithoutAdaptor
  Scenario Outline: place order for Gene fragment without adaptors

    Given user has existing order details
      | sfdc_id   |
      | <sfdc_id> |

    Given user has create inventory request for "shippable_fragment"
    When user invoke create inventory API
#    Then verify 200 response

    Given user has create shipment request for "shippable_fragment"
    When user invoke create shipment API
#    Then verify 200 response

    Given user has finalize shipment request
    When user invoke finalize shipment API
#    Then verify 200 response

    Given user has create invoice request with status "BOOKED"
    When user invoke invoice API
#    Then verify 200 response

    Given user has create invoice request with status "IN_TRANSIT"
    When user invoke invoice API
    Then user downloaded the invoice
    Then verify 200 response
    Examples:
      | sfdc_id            |
      | 8016C000000YYUWQA4 |


  @Forth @ClonalGene
  Scenario Outline: place order for Clonal gene
    Given user has existing order details
      | sfdc_id   |
      | <sfdc_id> |

    Given user has create inventory request for "shippable_plasmid_microprep"
    When user invoke create inventory API
    Then verify 200 response

    Given user has create shipment request for "shippable_plasmid_microprep"
    When user invoke create shipment API
    Then verify 200 response

    Given user has finalize shipment request
    When user invoke finalize shipment API
    Then verify 200 response

    Given user has create invoice request with status "BOOKED"
    When user invoke invoice API
    Then verify 200 response

    Given user has create invoice request with status "IN_TRANSIT"
    When user invoke invoice API
    Then verify 200 response
    Examples:
      | sfdc_id            |
      | 8016C000000YYUWQA4 |
Feature: Place E2E orders


  @ClonalGene
  Scenario Outline: place order for Clonal gene
    Given user generate random sequence with
#      | basePair          | 1000 |
#      | numberOfSequences | 1    |
    Given user has create construct request with
      | type        | CLONED_GENE |
      | adapters_on | false       |
    When user invoke create construct API
    Then verify 201 response

    Given user has create quote request
      | type                   | NA                |
      | order_sub_product_type | NA                |
      | delivery_format        | <delivery_format> |
    When user invoke create quote API
    Then verify 201 response
    Then verify the quote status is "SUCCESS"

    Given user has create order request
    When user invoke create order API
    Then verify 201 response

    Then user sync order to MES from SFDC

    When user invoke get order-items details and check sync happened at MES
    Then verify 200 response

    Examples:
      | order | delivery_format  |
#      | 1     | SER_PKG_TUBE     |
#      | 2     | SER_PKG_96_PCR  |
      | 3     | SER_PKG_96       |
#      | 4     | SER_PKG_384_ECHO |

  @GeneFragmentWithAdaptor
  Scenario Outline: place order for Gene fragment with adaptors
#    Given user generate random sequence with
#      | basePair          | 1000 |
#      | numberOfSequences | 1    |
    Given user has create construct request with
      | type        | NON_CLONED_GENE |
      | adapters_on | true            |

    When user invoke create construct API
    Then verify 201 response

    Given user has create quote request
      | type                   | NA |
      | order_sub_product_type | NA |
      | delivery_format        | <delivery_format> |
    When user invoke create quote API
    Then verify 201 response
    Then verify the quote status is "SUCCESS"

    Given user has create order request
    When user invoke create order API
    Then verify 201 response

    Then user sync order to MES from SFDC

    When user invoke get order-items details and check sync happened at MES
    Then verify 200 response
    Examples:
      | order | delivery_format  |
      | 1     | SER_PKG_TUBE     |
#      | 2     | SER_PKG_96_PCR  |
#      | 3     | SER_PKG_96       |
#      | 4     | SER_PKG_384_ECHO |

  @GeneFragmentWithoutAdaptor
  Scenario: place order for Gene fragment without adaptors
#    Given user generate random sequence with
#      | basePair          | 1000 |
#      | numberOfSequences | 1    |
    Given user has create construct request with
      | type        | NON_CLONED_GENE |
      | adapters_on | false           |
    When user invoke create construct API
    Then verify 201 response

    Given user has create quote request
      | type                   | NA                      |
      | order_sub_product_type | NON_CLONAL_ADAPTERS_OFF |
    When user invoke create quote API
    Then verify 201 response
    Then verify the quote status is "SUCCESS"

    Given user has create order request
    When user invoke create order API
    Then verify 201 response

    Then user sync order to MES from SFDC

    When user invoke get order-items details and check sync happened at MES
    Then verify 200 response

  @NGSKit
  Scenario: place order for NGS Kit
    Given place NGS kit order for existing user
    And user sync order to MES from SFDC
#
    When user invoke get order-items details and check sync happened at MES
    Then verify 200 response
    And user fetch order details

    And user test order packing and booking from Forth UI
      | wor_id | sfdc_id |
      | NA     | NA      |
    And user fetch order details

    Given user has create invoice request with status "BOOKED"
    When user invoke invoice API
    Then verify 200 response

    Given user has create invoice request with status "IN_TRANSIT"
    When user invoke invoice API
    Then verify 200 response

  @OligoPool
  Scenario: place order for Oligo pool
    Given user generate random sequence with
      | basePair          | 30 |
      | numberOfSequences | 10 |
    Given user has create construct request with
      | type        | OLIGO_POOL |
      | adapters_on | na         |
    When user invoke create construct API
    Then verify 201 response

    Given user has create quote request
      | type                   | TUBE |
      | order_sub_product_type | NA   |
    When user invoke create quote API
    Then verify 201 response
    Then verify the quote status is "SUCCESS"

    Given user has create order request
    When user invoke create order API
    Then verify 201 response

    Then user sync order to MES from SFDC

    When user invoke get order-items details and check sync happened at MES
    Then verify 200 response

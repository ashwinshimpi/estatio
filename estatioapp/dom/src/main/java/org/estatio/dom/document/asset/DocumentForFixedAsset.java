package org.estatio.dom.document.asset;

import javax.jdo.annotations.Column;

import org.estatio.dom.asset.FixedAsset;
import org.estatio.dom.document.Document;

@javax.jdo.annotations.PersistenceCapable
@javax.jdo.annotations.Inheritance(
        strategy = javax.jdo.annotations.InheritanceStrategy.SUPERCLASS_TABLE)
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findByFixedAsset", language = "JDOQL",
                value = "SELECT "
                        + "FROM org.estatio.dom.document.asset.DocumentForFixedAsset "
                        + "WHERE fixedAsset == :fixedAsset "),
        @javax.jdo.annotations.Query(
                name = "findByFixedAssetAndType", language = "JDOQL",
                value = "SELECT "
                        + "FROM org.estatio.dom.document.asset.DocumentForFixedAsset "
                        + "WHERE fixedAsset == :fixedAsset "
                        + "&& type == :type")
})
public class DocumentForFixedAsset extends Document {

    private FixedAsset fixedAsset;

    @Column(allowsNull = "false", name = "fixedAssetId")
    public FixedAsset getFixedAsset() {
        return fixedAsset;
    }

    public void setFixedAsset(FixedAsset fixedAsset) {
        this.fixedAsset = fixedAsset;
    }

}

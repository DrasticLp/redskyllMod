package com.drastic.redskyll.blockhit.config;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import com.drastic.redskyll.Main;
import com.google.common.collect.Sets;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class StringListParser
{

    private Optional<ResourceLocation> parseResourceLocation(String source)
    {

        String[] s = source.split(":");
        Optional<ResourceLocation> location = Optional.empty();
        if(s.length == 1)
        {

            location = Optional.of(new ResourceLocation(s[0]));
        }
        else if(s.length == 2)
        {

            location = Optional.of(new ResourceLocation(s[0], s[1]));
        }
        else
        {

            this.logStringParsingError(source, "Insufficient number of arguments");
        }

        return location;
    }

    private Optional<Item> getItemFromRegistry(ResourceLocation location)
    {

        Item item = Item.REGISTRY.getObject(location);
        if(item != null && item != Items.AIR)
        {

            return Optional.of(item);
        }
        else
        {

            this.logStringParsingError(location.toString(), "Item not found");
        }

        return Optional.empty();
    }

    private Optional<Item> getItemFromRegistry(String source)
    {

        Optional<ResourceLocation> location = this.parseResourceLocation(source);
        return location.isPresent() ? this.getItemFromRegistry(location.get()) : Optional.empty();
    }

    private void logStringParsingError(String item, String message)
    {
        Main.logger.error("Unable to parse entry \"" + item + "\": " + message);
    }

    public Set<Item> buildItemSetWithCondition(List<String> locations, Predicate<Item> condition, String message)
    {

        Set<Item> set = Sets.newHashSet();
        for(String source : locations)
        {

            this.parseResourceLocation(source).flatMap(this::getItemFromRegistry).ifPresent(item ->
            {

                if(condition.test(item))
                {

                    set.add(item);
                }
                else
                {

                    this.logStringParsingError(source, message);
                }
            });
        }

        return set;
    }

}

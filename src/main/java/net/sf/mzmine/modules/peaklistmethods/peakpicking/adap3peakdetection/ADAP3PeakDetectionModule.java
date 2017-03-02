/* 
 * Copyright (C) 2016 Du-Lab Team <dulab.binf@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package net.sf.mzmine.modules.peaklistmethods.peakpicking.adap3peakdetection;

import java.util.Collection;
import javax.annotation.Nonnull;
import net.sf.mzmine.datamodel.MZmineProject;
import net.sf.mzmine.datamodel.PeakList;
import net.sf.mzmine.modules.MZmineModuleCategory;
import net.sf.mzmine.modules.MZmineProcessingModule;
import net.sf.mzmine.modules.peaklistmethods.peakpicking.deconvolution.DeconvolutionParameters;
import net.sf.mzmine.parameters.ParameterSet;
import net.sf.mzmine.taskcontrol.Task;
import net.sf.mzmine.util.ExitCode;

/**
 *
 * @author aleksandrsmirnov
 */
public class ADAP3PeakDetectionModule implements MZmineProcessingModule {
    
    private static final String MODULE_NAME = "ADAP-GC Peak Detection";
    private static final String MODULE_DESCRIPTION = "This module detects EIC peaks";
    
    @Nonnull @Override
    public String getName() {
        return MODULE_NAME;
    }
    
    @Nonnull @Override
    public String getDescription() {
        return MODULE_DESCRIPTION;
    }
    
    @Nonnull @Override
    public MZmineModuleCategory getModuleCategory() {
        return MZmineModuleCategory.PEAKLISTPICKING;
    }
    
    @Nonnull @Override
    public Class<? extends ParameterSet> getParameterSetClass() {
        return ADAP3PeakDetectionParameters.class;
    }
    
    @Nonnull @Override
    public ExitCode runModule(@Nonnull MZmineProject project,
            @Nonnull final ParameterSet parameters,
            @Nonnull final Collection<Task> tasks) {
        
        for (final PeakList peakList : parameters
                .getParameter(DeconvolutionParameters.PEAK_LISTS).getValue()
                .getMatchingPeakLists()) {

            tasks.add(new ADAP3PeakDetectionTask(project, peakList, parameters));
        }
        
        return ExitCode.OK;
    }
}
